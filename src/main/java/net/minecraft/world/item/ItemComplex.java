// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.network.packet.Packet;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.level.Level;

public class ItemComplex extends Item
{
    protected ItemComplex(final int id) {
        super(id);
    }

    @Override
    public boolean isComplex() {
        return true;
    }

    public Packet getUpdatePacket(final ItemInstance itemInstance, final Level level, final EntityPlayer player) {
        return null;
    }
}
