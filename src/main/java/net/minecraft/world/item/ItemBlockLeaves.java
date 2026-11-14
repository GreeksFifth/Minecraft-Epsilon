// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.Facing;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.BlockLeaves;
import net.minecraft.world.level.block.Blocks;

public class ItemBlockLeaves extends ItemBlock
{
    public ItemBlockLeaves(final int id) {
        super(id);
        this.setMaxDamage(0);
        this.setStackedByData(true);
    }
    
    @Override
    public int getLevelDataForAuxValue(final int auxValue) {
        return auxValue | BlockLeaves.UPDATE_LEAF_BIT;
    }
    
    @Override
    public int getIcon(final int auxValue) {
        return Blocks.leaves.getTexture(Facing.DOWN, auxValue);
    }
    
    @Override
    public int getColor(final int auxData) {
        if ((auxData & BlockLeaves.LEAF_TYPE_MASK) == BlockLeaves.EVERGREEN_LEAF) {
            return FoliageColor.getEvergreenColor();
        }
        if ((auxData & BlockLeaves.LEAF_TYPE_MASK) == BlockLeaves.BIRCH_LEAF) {
            return FoliageColor.getBirchColor();
        }
        return FoliageColor.getDefaultColor();
    }
}
