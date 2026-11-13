// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.tile;

import java.util.Random;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Material;

public class BlockLogicClay extends Block
{
    public BlockLogicClay(final int id, final int tex) {
        super(id, tex, Material.clay);
    }
    
    @Override
    public int getResource(final int data, final Random random) {
        return Items.clay.id;
    }
    
    @Override
    public int getResourceCount(final Random random) {
        return 4;
    }
}
