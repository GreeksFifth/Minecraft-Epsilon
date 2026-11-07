package link.e4steam.steam;

public final class SteamConnectionProgress {
    private static volatile String message = "Starting Steam networking...";

    private SteamConnectionProgress() {
    }

    public static String message() {
        return message;
    }

    public static void update(String next) {
        if (next != null && !next.isBlank()) {
            message = next;
        }
    }
}
