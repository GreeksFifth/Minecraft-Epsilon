// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import net.minecraft.world.item.Items;

import java.util.Random;

public class BlockLogicGravel extends BlockLogicSand
{
    public BlockLogicGravel(final int id, final int tex) {
        super(id, tex);
    }
    
    @Override
    public int getResource(final int data, final Random random) {
        if (random.nextInt(10) == 0) return Items.flint.id;
        return this.id;
    }
}
