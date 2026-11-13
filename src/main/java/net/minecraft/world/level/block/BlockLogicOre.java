// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import net.minecraft.world.item.DyePowderItem;

import java.util.Random;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Material;

public class BlockLogicOre extends Block
{
    public BlockLogicOre(final int id, final int tex) {
        super(id, tex, Material.stone);
    }
    
    @Override
    public int getResource(final int data, final Random random) {
        if (this.id == Block.coalOre.id) return Items.coal.id;
        if (this.id == Block.diamondOre.id) return Items.diamond.id;
        if (this.id == Block.lapisOre.id) return Items.dye_powder.id;
        return this.id;
    }
    
    @Override
    public int getResourceCount(final Random random) {
        if (this.id == Block.lapisOre.id) return 4 + random.nextInt(5);
        return 1;
    }
    
    @Override
    protected int getSpawnResourcesAuxValue(final int data) {
        if (this.id == Block.lapisOre.id) return DyePowderItem.BLUE;
        return 0;
    }
}
