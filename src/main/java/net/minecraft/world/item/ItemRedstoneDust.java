// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.Facing;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.level.block.Blocks;

public class ItemRedstoneDust extends Item
{
    public ItemRedstoneDust(final int id) {
        super(id);
    }
    
    @Override
    public boolean useOn(final ItemInstance itemInstance, final EntityPlayer player, final Level level, int x, int y, int z, final int face) {
        if (level.getTile(x, y, z) != Blocks.topSnow.id) {
            if (face == Facing.DOWN) y--;
            if (face == Facing.UP) y++;
            if (face == Facing.NORTH) z--;
            if (face == Facing.SOUTH) z++;
            if (face == Facing.WEST) x--;
            if (face == Facing.EAST) x++;

            if (!level.isEmptyTile(x, y, z)) return false;
        }
        if (Blocks.redStoneDust.mayPlace(level, x, y, z)) {
            itemInstance.count--;
            level.setTile(x, y, z, Blocks.redStoneDust.id);
        }

        return true;
    }
}
