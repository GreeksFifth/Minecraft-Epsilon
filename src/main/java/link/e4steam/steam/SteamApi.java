package link.e4steam.steam;

interface SteamApi {
    boolean loadLibraries(SteamNativeLibraryLoader loader);

    boolean init() throws Exception;

    boolean isSteamRunning();

    void runCallbacks();

    void shutdown();
}
