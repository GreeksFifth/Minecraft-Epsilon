// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.entity.projectile.EntityProjectileArrow;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.level.Level;

public class ItemBow extends Item
{
    public ItemBow(final int id) {
        super(id);
        this.maxStackSize = 1;
    }
    
    @Override
    public ItemInstance use(final ItemInstance itemInstance, final Level level, final EntityPlayer player) {
        if (player.inventory.removeResource(Items.arrow.id)) {
            level.playSound(player, "random.bow", 1.0f, 1.0f / (ItemBow.random.nextFloat() * 0.4f + 0.8f));
            if (!level.isClientSide) {
                level.addEntity(new EntityProjectileArrow(level, player));
            }
        }

        return itemInstance;
    }
}
