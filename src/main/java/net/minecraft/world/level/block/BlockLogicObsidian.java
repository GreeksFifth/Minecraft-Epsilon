// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import java.util.Random;

public class BlockLogicObsidian extends BlockLogicStone
{
    public BlockLogicObsidian(final int id, final int tex) {
        super(id, tex);
    }
    
    @Override
    public int getResourceCount(final Random random) {
        return 1;
    }
    
    @Override
    public int getResource(final int data, final Random random) {
        return Block.obsidian.id;
    }
}
