// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.server.level;

import net.minecraft.world.item.ItemInstance;
import net.minecraft.network.packet.TileUpdatePacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.player.Player;

public class ServerPlayerGameMode
{
    private ServerLevel level;
    public Player player;
    private float destroyProgress = 0.0f; // Useless - Assuming this class is copied from regular gamemode classes, this seems most likely to be destroyProgress still
    private int destroyProgressStart;
    private int xDestroyBlock, yDestroyBlock, zDestroyBlock;
    private int gameTicks;
    private boolean hasDelayedDestroy;
    private int delayedDestroyX, delayedDestroyY, delayedDestroyZ;
    private int delayedTickStart;
    
    public ServerPlayerGameMode(final ServerLevel level) {
        this.level = level;
    }

    public void tick() {
        this.gameTicks++;

        if (this.hasDelayedDestroy) {
            final int ticksSpentDestroying = this.gameTicks - this.delayedTickStart;
            final int t = this.level.getTile(this.delayedDestroyX, this.delayedDestroyY, this.delayedDestroyZ);
            if (t == 0) {
                this.hasDelayedDestroy = false;
            } else {
                Block block = Block.BLOCKS[t];
                float destroyProgress = block.getDestroyProgress(this.player) * (ticksSpentDestroying + 1);

                if (destroyProgress >= 1.0f) {
                    this.hasDelayedDestroy = false;
                    this.destroyBlock(this.delayedDestroyX, this.delayedDestroyY, this.delayedDestroyZ);
                }
            }
        }
    }
    
    public void startDestroyBlock(final int x, final int y, final int z, final int face) {
        this.level.extinguishFire(null, x, y, z, face);
        this.destroyProgressStart = this.gameTicks;
        final int t = this.level.getTile(x, y, z);
        if (t > 0) {
            Block.BLOCKS[t].attack(this.level, x, y, z, this.player);
        }

        if (t > 0 && Block.BLOCKS[t].getDestroyProgress(this.player) >= 1.0f) {
            this.destroyBlock(x, y, z);
        }
        else {
            this.xDestroyBlock = x;
            this.yDestroyBlock = y;
            this.zDestroyBlock = z;
        }
    }
    
    public void stopDestroyBlock(final int x, final int y, final int z) {
        if (x == this.xDestroyBlock && y == this.yDestroyBlock && z == this.zDestroyBlock) {
            final int ticksSpentDestroying = this.gameTicks - this.destroyProgressStart;

            final int t = this.level.getTile(x, y, z);
            if (t != 0) {
                Block block = Block.BLOCKS[t];
                float destroyProgress = block.getDestroyProgress(this.player) * (ticksSpentDestroying + 1);
                if (destroyProgress >= 0.7f) {
                    this.destroyBlock(x, y, z);
                }
                else if (!this.hasDelayedDestroy) {
                    this.hasDelayedDestroy = true;
                    this.delayedDestroyX = x;
                    this.delayedDestroyY = y;
                    this.delayedDestroyZ = z;
                    this.delayedTickStart = this.destroyProgressStart;
                }
            }
        }
        this.destroyProgress = 0.0f;
    }

    public boolean superDestroyBlock(final int x, final int y, final int z) {
        final Block oldBlock = Block.BLOCKS[this.level.getTile(x, y, z)];
        final int data = this.level.getData(x, y, z);

        final boolean changed = this.level.setTile(x, y, z, 0);
        if (oldBlock != null && changed) {
            oldBlock.destroy(this.level, x, y, z, data);
        }
        return changed;
    }
    
    public boolean destroyBlock(final int x, final int y, final int z) {
        final int t = this.level.getTile(x, y, z);
        final int data = this.level.getData(x, y, z);

        this.level.levelEvent(this.player, LevelEvent.PARTICLES_DESTROY_BLOCK, x, y, z, t + this.level.getData(x, y, z) * Block.TILE_NUM_COUNT);

        final boolean changed = this.superDestroyBlock(x, y, z);

        final ItemInstance item = this.player.getSelectedItem();
        if (item != null) {
            item.mineBlock(t, x, y, z, this.player);
            if (item.count == 0) {
                item.snap(this.player);
                this.player.removeSelectedItem();
            }
        }
        boolean canDestroy = this.player.canDestroy(Block.BLOCKS[t]);
        if (changed && canDestroy) {
            Block.BLOCKS[t].playerDestroy(this.level, this.player, x, y, z, data);
            ((ServerPlayer)this.player).connection.send(new TileUpdatePacket(x, y, z, this.level));
        }
        return changed;
    }
    
    public boolean useItem(final Player player, final Level level, final ItemInstance item) {
        final int oldCount = item.count;
        final ItemInstance itemInstance = item.use(level, player);
        if (itemInstance != item || (itemInstance != null && itemInstance.count != oldCount)) {
            player.inventory.items[player.inventory.selected] = itemInstance;
            if (itemInstance.count == 0) {
                player.inventory.items[player.inventory.selected] = null;
            }
            return true;
        }
        return false;
    }
    
    public boolean useItemOn(final Player player, final Level level, final ItemInstance item, final int x, final int y, final int z, final int face) {
        final int t = level.getTile(x, y, z);
        return (t > 0 && Block.BLOCKS[t].use(level, x, y, z, player)) || (item != null && item.useOn(player, level, x, y, z, face));
    }
}
