// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.entity.projectile.EntityProjectileSnowball;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.level.Level;

public class ItemSnowball extends Item
{
    public ItemSnowball(final int id) {
        super(id);
        this.maxStackSize = 16;
    }
    
    @Override
    public ItemInstance use(final ItemInstance itemInstance, final Level level, final EntityPlayer player) {
        itemInstance.count--;
        level.playSound(player, "random.bow", 0.5f, 0.4f / (ItemSnowball.random.nextFloat() * 0.4f + 0.8f));
        if (!level.isClientSide) level.addEntity(new EntityProjectileSnowball(level, player));
        return itemInstance;
    }
}
