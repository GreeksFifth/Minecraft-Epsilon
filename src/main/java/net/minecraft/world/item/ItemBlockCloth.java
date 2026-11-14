// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.Facing;
import net.minecraft.world.level.block.BlockWool;
import net.minecraft.world.level.block.Blocks;

public class ItemBlockCloth extends ItemBlock
{
    public ItemBlockCloth(final int id) {
        super(id);
        this.setMaxDamage(0);
        this.setStackedByData(true);
    }
    
    @Override
    public int getIcon(final int auxValue) {
        return Blocks.cloth.getTexture(Facing.NORTH, BlockWool.getTileDataForItemAuxValue(auxValue));
    }
    
    @Override
    public int getLevelDataForAuxValue(final int auxValue) {
        return auxValue;
    }
    
    @Override
    public String getDescriptionId(final ItemInstance itemInstance) {
        return super.getDescriptionId() + "." + ItemDyePowder.COLOR_DESCS[BlockWool.getTileDataForItemAuxValue(itemInstance.getAuxValue())];
    }
}
