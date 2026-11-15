package net.minecraft.midforddevutils;

import net.minecraft.client.renderer.Tesselator;

public class MidfordBlitter {
    public static void blit(final int x, final int y,final int blitOffset, final int sx, final int sy, final int w, final int h) {
        final float us = 1 / 256.0f;
        final float vs = 1 / 256.0f;
        final Tesselator t = Tesselator.instance;
        t.begin();
        t.vertexUV(x + 0, y + h, blitOffset, (sx + 0) * us, (sy + h) * vs);
        t.vertexUV(x + w, y + h, blitOffset, (sx + w) * us, (sy + h) * vs);
        t.vertexUV(x + w, y + 0, blitOffset, (sx + w) * us, (sy + 0) * vs);
        t.vertexUV(x + 0, y + 0, blitOffset, (sx + 0) * us, (sy + 0) * vs);
        t.end();
    }
}
