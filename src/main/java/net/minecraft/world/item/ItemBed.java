// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.Direction;
import net.minecraft.Facing;
import net.minecraft.world.level.block.Blocks;
import util.Mth;
import net.minecraft.world.level.block.BlockBed;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.EntityPlayer;

public class ItemBed extends Item
{
    public ItemBed(final int id) {
        super(id);
    }
    
    @Override
    public boolean useOn(final ItemInstance itemInstance, final EntityPlayer player, final Level level, final int x, int y, final int z, final int face) {
        if (face != Facing.UP) return false;

        // place on top of tile
        y = y + 1;

        final BlockBed tile = (BlockBed) Blocks.bed;

        final int dir = Mth.floor(player.yRot * 4.0f / 360.0f + 0.5) & 0x3;
        int xra = 0;
        int zra = 0;

        if (dir == Direction.SOUTH) zra = 1;
        if (dir == Direction.WEST) xra = -1;
        if (dir == Direction.NORTH) zra = -1;
        if (dir == Direction.EAST) xra = 1;

        if (level.isEmptyTile(x, y, z) && level.isEmptyTile(x + xra, y, z + zra) && level.isSolidBlockingTile(x, y - 1, z) && level.isSolidBlockingTile(x + xra, y - 1, z + zra)) {
            level.setTileAndData(x, y, z, tile.id, dir);
            level.setTileAndData(x + xra, y, z + zra, tile.id, dir + BlockBed.HEAD_PIECE_DATA);
            itemInstance.count--;
            return true;
        }

        return false;
    }
}
