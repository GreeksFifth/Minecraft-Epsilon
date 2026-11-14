// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.Facing;
import net.minecraft.world.entity.EntityPainting;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.EntityPlayer;

public class ItemPainting extends Item
{
    public ItemPainting(final int id) {
        super(id);
    }
    
    @Override
    public boolean useOn(final ItemInstance itemInstance, final EntityPlayer player, final Level level, final int x, final int y, final int z, final int face) {
        if (face == Facing.DOWN) return false;
        if (face == Facing.UP) return false;

        int dir = 0;
        if (face == Facing.WEST) dir = 1;
        if (face == Facing.SOUTH) dir = 2;
        if (face == Facing.EAST) dir = 3;

        final EntityPainting painting = new EntityPainting(level, x, y, z, dir);
        if (painting.survives()) {
            if (!level.isClientSide) {
                level.addEntity(painting);
            }
            itemInstance.count--;
        }
        return true;
    }
}
