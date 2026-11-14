// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.Facing;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.tileentity.TileEntitySign;
import util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.EntityPlayer;

public class ItemSign extends Item
{
    public ItemSign(final int id) {
        super(id);
        this.maxStackSize = 1;
    }
    
    @Override
    public boolean useOn(final ItemInstance itemInstance, final EntityPlayer player, final Level level, int x, int y, int z, final int face) {
        if (face == Facing.DOWN) return false;
        if (!level.getMaterial(x, y, z).isSolid()) return false;

        if (face == Facing.UP) ++y;
        if (face == Facing.NORTH) --z;
        if (face == Facing.SOUTH) ++z;
        if (face == Facing.WEST) --x;
        if (face == Facing.EAST) ++x;

        if (!Blocks.sign.mayPlace(level, x, y, z)) return false;

        if (face == 1) {
            int rot = Mth.floor((player.yRot + 180.0f) * 16.0f / 360.0f + 0.5) & 0xF;
            level.setTileAndData(x, y, z, Blocks.sign.id, rot);
        }
        else {
            level.setTileAndData(x, y, z, Blocks.wallSign.id, face);
        }

        itemInstance.count--;
        final TileEntitySign sign = (TileEntitySign)level.getTileEntity(x, y, z);
        if (sign != null) player.openTextEdit(sign);
        return true;
    }
}
