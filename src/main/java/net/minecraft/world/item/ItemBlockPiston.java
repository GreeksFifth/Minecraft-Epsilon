// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.block.BlockPistonBase;

public class ItemBlockPiston extends ItemBlock
{
    public ItemBlockPiston(final int id) {
        super(id);
    }
    
    @Override
    public int getLevelDataForAuxValue(final int auxValue) {
        return BlockPistonBase.UNDEFINED_FACING;
    }
}
