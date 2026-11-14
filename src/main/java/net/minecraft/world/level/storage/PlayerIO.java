// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.storage;

import com.mojang.nbt.CompoundTag;
import net.minecraft.world.entity.player.EntityPlayer;

public interface PlayerIO
{
    void save(final EntityPlayer player);
    
    void load(final EntityPlayer player);
    CompoundTag loadPlayerDataTag(final String userName);
}
