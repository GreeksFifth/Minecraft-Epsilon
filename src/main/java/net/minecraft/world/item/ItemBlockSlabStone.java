// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.Facing;
import net.minecraft.world.level.block.BlockSlab;
import net.minecraft.world.level.block.Blocks;

public class ItemBlockSlabStone extends ItemBlock
{
    public ItemBlockSlabStone(final int id) {
        super(id);
        this.setMaxDamage(0);
        this.setStackedByData(true);
    }
    
    @Override
    public int getIcon(final int auxValue) {
        return Blocks.stoneSlabHalf.getTexture(Facing.NORTH, auxValue);
    }
    
    @Override
    public int getLevelDataForAuxValue(final int auxValue) {
        return auxValue;
    }
    
    @Override
    public String getDescriptionId(final ItemInstance itemInstance) {
        return super.getDescriptionId() + "." + BlockSlab.SLAB_NAMES[itemInstance.getAuxValue()];
    }
}
