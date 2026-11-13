// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.tile;

import java.util.Random;

public class BlockLogicDeadBush extends BlockLogicBush
{
    protected BlockLogicDeadBush(final int id, final int tex) {
        super(id, tex);
        final float ss = 0.4f;
        this.setShape(0.5f - ss, 0.0f, 0.5f - ss, 0.5f + ss, 0.8f, 0.5f + ss);
    }
    
    @Override
    protected boolean mayPlaceOn(final int tile) {
        return tile == Block.sand.id;
    }
    
    @Override
    public int getTexture(final int face, final int data) {
        return this.tex;
    }
    
    @Override
    public int getResource(final int data, final Random random) {
        return -1;
    }
}
