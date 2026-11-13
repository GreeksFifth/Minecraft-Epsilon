// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.Facing;
import util.Mth;
import net.minecraft.world.level.tile.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Material;

public class DoorItem extends Item
{
    private Material material;
    
    public DoorItem(final int id, final Material material) {
        super(id);
        this.material = material;
        this.maxStackSize = 1;
    }
    
    @Override
    public boolean useOn(final ItemInstance itemInstance, final Player player, final Level level, final int x, int y, final int z, final int face) {
        if (face != Facing.UP) return false;
        y++;

        Block block;

        if (this.material == Material.wood) block = Block.door_wood;
        else block = Block.door_iron;

        if (!block.mayPlace(level, x, y, z)) return false;

        int dir = Mth.floor((player.yRot + 180.0f) * 4.0f / 360.0f - 0.5) & 0x3;

        int xra = 0;
        int zra = 0;
        if (dir == 0) zra = 1;
        if (dir == 1) xra = -1;
        if (dir == 2) zra = -1;
        if (dir == 3) xra = 1;

        final int solidLeft = (level.isSolidBlockingTile(x - xra, y, z - zra) ? 1 : 0) + (level.isSolidBlockingTile(x - xra, y + 1, z - zra) ? 1 : 0);
        final int solidRight = (level.isSolidBlockingTile(x + xra, y, z + zra) ? 1 : 0) + (level.isSolidBlockingTile(x + xra, y + 1, z + zra) ? 1 : 0);

        final boolean doorLeft = level.getTile(x - xra, y, z - zra) == block.id || level.getTile(x - xra, y + 1, z - zra) == block.id;
        final boolean doorRight = level.getTile(x + xra, y, z + zra) == block.id || level.getTile(x + xra, y + 1, z + zra) == block.id;

        boolean flip = false;
        if (doorLeft && !doorRight) flip = true;
        else if (solidRight > solidLeft) flip = true;

        if (flip) {
            dir = (dir - 1 & 0x3);
            dir += 4;
        }

        level.noNeighborUpdate = true;
        level.setTileAndData(x, y, z, block.id, dir);
        level.setTileAndData(x, y + 1, z, block.id, dir + 8);
        level.noNeighborUpdate = false;
        level.updateNeighborsAt(x, y, z, block.id);
        level.updateNeighborsAt(x, y + 1, z, block.id);
        itemInstance.count--;
        return true;
    }
}
