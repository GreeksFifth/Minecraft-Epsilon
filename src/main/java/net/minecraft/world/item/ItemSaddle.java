// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.entity.animal.EntityPig;
import net.minecraft.world.entity.EntityMob;

public class ItemSaddle extends Item
{
    public ItemSaddle(final int id) {
        super(id);
        this.maxStackSize = 1;
    }
    
    @Override
    public void interactEnemy(final ItemInstance itemInstance, final EntityMob mob) {
        if (mob instanceof EntityPig) {
            final EntityPig pig = (EntityPig)mob;
            if (!pig.hasSaddle()) {
                pig.setSaddle(true);
                itemInstance.count--;
            }
        }
    }
    
    @Override
    public boolean hurtEnemy(final ItemInstance itemInstance, final EntityMob mob, final EntityMob attacker) {
        this.interactEnemy(itemInstance, mob);
        return true;
    }
}
