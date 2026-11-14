// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.Facing;
import net.minecraft.world.level.block.Blocks;

public class ItemBlockLog extends ItemBlock
{
    public ItemBlockLog(final int id) {
        super(id);
        this.setMaxDamage(0);
        this.setStackedByData(true);
    }
    
    @Override
    public int getIcon(final int auxValue) {
        return Blocks.treeTrunk.getTexture(Facing.NORTH, auxValue);
    }
    
    @Override
    public int getLevelDataForAuxValue(final int auxValue) {
        return auxValue;
    }
}
