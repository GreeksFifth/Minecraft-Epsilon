// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.block.Blocks;

public class ItemBlockSapling extends ItemBlock
{
    public ItemBlockSapling(final int id) {
        super(id);
        this.setMaxDamage(0);
        this.setStackedByData(true);
    }
    
    @Override
    public int getLevelDataForAuxValue(final int auxValue) {
        return auxValue;
    }
    
    @Override
    public int getIcon(final int auxValue) {
        return Blocks.sapling.getTexture(0, auxValue);
    }
}
