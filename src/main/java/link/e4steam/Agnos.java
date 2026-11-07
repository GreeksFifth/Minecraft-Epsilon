package link.e4steam;

import java.nio.file.Path;

public final class Agnos {
    private static volatile boolean dedicatedServer;
    private static volatile Path configDir = Path.of("config");

    private Agnos() {}

    public static boolean isClient() {
        return true;
    }

    public static boolean isDedicatedServer() {
        return dedicatedServer;
    }

    public static Path configDir() {
        return configDir;
    }

    public static void setup(boolean server, Path gameDir) {
        dedicatedServer = server;
        configDir = gameDir.resolve("config");
    }
}
