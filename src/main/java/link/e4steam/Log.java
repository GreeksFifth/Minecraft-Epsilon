package link.e4steam;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Log {
    private static final boolean DEBUG = Boolean.getBoolean("e4steam.debug");
    private final String prefix;

    public Log(String name) {
        this.prefix = "[" + name + "] ";
    }

    public void debug(String message, Object... args) {
        if (DEBUG) log(Level.INFO, message, args);
    }

    public void info(String message, Object... args) {
        log(Level.INFO, message, args);
    }

    public void warn(String message, Object... args) {
        log(Level.WARNING, message, args);
    }

    public void error(String message, Object... args) {
        log(Level.SEVERE, message, args);
    }

    private void log(Level level, String message, Object... args) {
        Throwable thrown = null;
        int count = args.length;
        if (count > 0 && args[count - 1] instanceof Throwable) {
            thrown = (Throwable) args[count - 1];
            count--;
        }

        StringBuilder sb = new StringBuilder(prefix);
        int argIndex = 0;
        int start = 0;
        int pos;
        while ((pos = message.indexOf("{}", start)) >= 0) {
            sb.append(message, start, pos);
            if (argIndex < count) sb.append(args[argIndex++]);
            else sb.append("{}");
            start = pos + 2;
        }
        sb.append(message, start, message.length());

        if (Agnos.isDedicatedServer()) {
            Logger.getLogger("Minecraft").log(level, sb.toString(), thrown);
        }
        else {
            System.out.println(sb);
            if (thrown != null) thrown.printStackTrace();
        }
    }
}
