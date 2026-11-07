package link.e4steam;

import link.e4steam.steam.SteamAccessMode;
import link.e4steam.steam.SteamAddress;
import link.e4steam.steam.SteamClientBridge;
import link.e4steam.steam.SteamConnectionProgress;
import link.e4steam.steam.SteamRuntime;
import link.e4steam.steam.SteamSession;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class E4steamClient {
    public static final Log LOGGER = new Log("e4Epsilon");
    public static volatile SteamSession session;
    private static volatile SteamRuntime.Activity clientActivity;
    private static volatile String lastFailure;
    private static final ConcurrentLinkedQueue<String> invites = new ConcurrentLinkedQueue<>();
    private static final ConcurrentLinkedQueue<String> failures = new ConcurrentLinkedQueue<>();
    private static int presenceTicks;

    private E4steamClient() {}

    public static boolean isSteamAddress(String host) {
        return SteamAddress.tryParse(host).isPresent();
    }

    public static void startServer(Path serverDir, int port, SteamAccessMode accessMode) {
        if (session != null) return;
        Agnos.setup(true, serverDir);
        SteamRuntime.preloadCompatibilityClasses();
        SteamSession created = new SteamSession(port, accessMode);
        session = created;
        created.startAsync();
        LOGGER.info("Starting Steam tunnel for port {}", port);
    }

    public static void tickServer(String version, int protocol, int players, int maxPlayers) {
        if (session == null || ++presenceTicks < 100) return;
        presenceTicks = 0;
        SteamRuntime.get().updateHostDetails(null, null, version, protocol, players, maxPlayers);
    }

    public static void startClient(Path gameDir) {
        if (!isSteamProcessRunning()) {
            LOGGER.info("Steam isn't running, invites won't work until it is");
            return;
        }
        Thread thread = new Thread(() -> {
            try {
                startClientRuntime(gameDir);
                LOGGER.info("Steam is ready");
            }
            catch (IOException e) {
                LOGGER.info("Steam is not available, invites won't work until it is");
            }
        }, "e4epsilon-client-start");
        thread.setDaemon(true);
        thread.start();
    }

    private static boolean isSteamProcessRunning() {
        try {
            return ProcessHandle.allProcesses().anyMatch(process -> process.info().command().map(command -> {
                String name = Path.of(command).getFileName().toString().toLowerCase();
                return name.equals("steam.exe") || name.equals("steam");
            }).orElse(false));
        }
        catch (RuntimeException e) {
            return false;
        }
    }

    public static InetSocketAddress connect(Path gameDir, String host) throws IOException {
        SteamAddress address = SteamAddress.tryParse(host).orElseThrow(() -> new ConnectException("Invalid Steam address"));
        lastFailure = null;
        SteamConnectionProgress.update("Starting Steam...");
        try {
            startClientRuntime(gameDir);
        }
        catch (IOException e) {
            LOGGER.warn("Could not start Steam", e);
            throw new ConnectException("Could not start Steam, is it running?");
        }

        SteamConnectionProgress.update("Finding Steam host...");
        boolean ready;
        try {
            ready = SteamRuntime.get().prepareDirectConnect(host).get(35, TimeUnit.SECONDS);
        }
        catch (TimeoutException e) {
            throw new ConnectException("Steam host did not respond");
        }
        catch (ExecutionException e) {
            throw new ConnectException(e.getCause() == null ? e.toString() : e.getCause().getMessage());
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectException("Interrupted");
        }
        if (!ready) {
            throw new ConnectException(lastFailure != null ? lastFailure : "Could not join the Steam host's lobby");
        }

        InetSocketAddress local = SteamClientBridge.open(address);
        SteamConnectionProgress.update("Connecting to the server...");
        return local;
    }

    public static void cancelConnect() {
        if (clientActivity == null) return;
        SteamClientBridge.cancelPending();
        SteamRuntime.get().cancelGuestJoin();
    }

    private static synchronized void startClientRuntime(Path gameDir) throws IOException {
        if (clientActivity != null) return;
        Agnos.setup(false, gameDir);
        SteamRuntime.preloadCompatibilityClasses();
        SteamRuntime runtime = SteamRuntime.get();
        SteamRuntime.Activity activity = runtime.acquireActivity();
        try {
            runtime.awaitReady();
        }
        catch (IOException | RuntimeException e) {
            activity.close();
            throw e instanceof IOException ? (IOException) e : new IOException(e);
        }
        clientActivity = activity;
    }

    public static void acceptSteamInvite(String endpoint, String hostName) {
        if (Agnos.isDedicatedServer()) return;
        if (!isSteamAddress(endpoint)) {
            failures.add("Invalid Steam address");
            return;
        }
        lastFailure = null;
        SteamRuntime.get().beginGuestConnect(endpoint).whenComplete((accepted, failure) -> {
            if (failure == null && Boolean.TRUE.equals(accepted)) {
                LOGGER.info("Joining {} through Steam", hostName);
                invites.add(endpoint);
            }
            else if (failure != null) {
                failures.add(failure.getMessage());
            }
            else {
                failures.add(lastFailure != null ? lastFailure : "The invitation expired");
            }
        });
    }

    public static String pollInvite() {
        return invites.poll();
    }

    public static String pollFailure() {
        return failures.poll();
    }

    public static void showSteamJoinFailure(String detail) {
        lastFailure = detail;
        LOGGER.warn("Steam join failed: {}", detail);
    }

    public static void shutdown() {
        SteamSession current = session;
        SteamRuntime.Activity activity = clientActivity;
        if (current == null && activity == null) return;
        session = null;
        clientActivity = null;
        if (current != null) current.stop();
        if (activity != null) activity.close();
        SteamRuntime.get().shutdown();
    }
}
