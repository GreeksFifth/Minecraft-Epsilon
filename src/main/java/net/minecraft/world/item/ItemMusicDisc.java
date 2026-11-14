// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.LevelEvent;
import net.minecraft.world.level.block.BlockJukebox;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.level.block.Blocks;

public class ItemMusicDisc extends Item
{
    public final String recording;
    
    protected ItemMusicDisc(final int id, final String recording) {
        super(id);
        this.recording = recording;
        this.maxStackSize = 1;
    }
    
    @Override
    public boolean useOn(final ItemInstance itemInstance, final EntityPlayer player, final Level level, final int x, final int y, final int z, final int face) {
        if (level.getTile(x, y, z) == Blocks.recordPlayer.id && level.getData(x, y, z) == 0) {
            if (level.isClientSide) return true;

            ((BlockJukebox) Blocks.recordPlayer).setRecord(level, x, y, z, this.id);
            level.levelEvent(null, LevelEvent.SOUND_PLAY_RECORDING, x, y, z, this.id);
            itemInstance.count--;
            return true;
        }
        return false;
    }
}
