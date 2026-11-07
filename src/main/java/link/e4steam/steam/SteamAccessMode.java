package link.e4steam.steam;

public enum SteamAccessMode {
    LOCAL_ONLY("text.e4steam_minecraft.access.local"),
    FRIENDS_ONLY("text.e4steam_minecraft.access.friends"),
    INVITE_ONLY("text.e4steam_minecraft.access.invite");

    private final String translationKey;

    SteamAccessMode(String translationKey) {
        this.translationKey = translationKey;
    }

    public String translationKey() {
        return translationKey;
    }
}
