// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.Facing;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
/*
    TODO: Figure out what the fuck this is supposed to be and unfuck/remove it.
            -Midford
 */
@Deprecated
public class ItemSpecialBlockPlanter extends Item
{
    private int tileId;
    
    public ItemSpecialBlockPlanter(final int id, final Block block) {
        super(id);
        this.tileId = block.id;
    }
    
    @Override
    public boolean useOn(final ItemInstance itemInstance, final EntityPlayer player, final Level level, int x, int y, int z, int face) {
        if (level.getTile(x, y, z) == Blocks.topSnow.id) {
            face = Facing.DOWN; // Useless - LCE had this as Facing::UP, which is not what it is set to in b1.7.3, presumably this is just a b1.7.3 bug where the facing direction for this is just incorrect
        }
        else {
            if (face == Facing.DOWN) y--;
            if (face == Facing.UP) y++;
            if (face == Facing.NORTH) z--;
            if (face == Facing.SOUTH) z++;
            if (face == Facing.WEST) x--;
            if (face == Facing.EAST) x++;
        }

        if (itemInstance.count == 0) return false;

        if (level.mayPlace(this.tileId, x, y, z, false, face)) {
            final Block block = Block.BLOCKS[this.tileId];
            if (level.setTile(x, y, z, this.tileId)) {
                Block.BLOCKS[this.tileId].setPlacedOnFace(level, x, y, z, face);
                Block.BLOCKS[this.tileId].setPlacedBy(level, x, y, z, player);

                level.playSound(x + 0.5f, y + 0.5f, z + 0.5f, block.soundType.getStepSound(), (block.soundType.getVolume() + 1.0f) / 2.0f, block.soundType.getPitch() * 0.8f);
                itemInstance.count--;
            }
        }
        return true;
    }
}
