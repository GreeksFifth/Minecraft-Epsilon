package link.e4steam;

import net.minecraft.locale.language.Language;

public final class Mirror {
    private Mirror() {}

    public static String translatable(String key, Object... args) {
        if (args.length == 0) return Language.getInstance().getElement(key);
        return Language.getInstance().getElement(key, args);
    }
}
