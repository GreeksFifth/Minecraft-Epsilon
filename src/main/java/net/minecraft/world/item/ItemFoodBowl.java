// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.level.Level;
/*
    TODO: Make the refund item agnostic, rename (ItemFoodRefundable or something),
      so that the returned item is a param on item creation instead of being hardcoded to a bowl.
        -Midford
 */
public class ItemFoodBowl extends ItemFood
{
    public ItemFoodBowl(final int id, final int nutrition) {
        super(id, nutrition, false);
    }
    
    @Override
    public ItemInstance use(final ItemInstance itemInstance, final Level level, final EntityPlayer player) {
        super.use(itemInstance, level, player);

        return new ItemInstance(Items.bowl);
    }
}
