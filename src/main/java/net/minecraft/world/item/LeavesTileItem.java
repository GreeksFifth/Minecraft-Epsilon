// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.Facing;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.tile.BlockLogicLeaves;
import net.minecraft.world.level.tile.Block;

public class LeavesTileItem extends TileItem
{
    public LeavesTileItem(final int id) {
        super(id);
        this.setMaxDamage(0);
        this.setStackedByData(true);
    }
    
    @Override
    public int getLevelDataForAuxValue(final int auxValue) {
        return auxValue | BlockLogicLeaves.UPDATE_LEAF_BIT;
    }
    
    @Override
    public int getIcon(final int auxValue) {
        return Block.leaves.getTexture(Facing.DOWN, auxValue);
    }
    
    @Override
    public int getColor(final int auxData) {
        if ((auxData & BlockLogicLeaves.LEAF_TYPE_MASK) == BlockLogicLeaves.EVERGREEN_LEAF) {
            return FoliageColor.getEvergreenColor();
        }
        if ((auxData & BlockLogicLeaves.LEAF_TYPE_MASK) == BlockLogicLeaves.BIRCH_LEAF) {
            return FoliageColor.getBirchColor();
        }
        return FoliageColor.getDefaultColor();
    }
}
