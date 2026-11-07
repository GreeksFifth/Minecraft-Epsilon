package link.e4steam;

import net.minecraft.SharedConstants;

public final class MinecraftVersion {
    private MinecraftVersion() {}

    public static String current() {
        return "epsilon-" + SharedConstants.NETWORK_PROTOCOL_VERSION;
    }
}
