// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.inventory;

import net.minecraft.stats.Achievements;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.item.Items;

public class FurnaceResultSlot extends Slot
{
    private EntityPlayer player;
    
    public FurnaceResultSlot(final EntityPlayer player, final Container container, final int slot, final int x, final int y) {
        super(container, slot, x, y);
        this.player = player;
    }
    
    @Override
    public boolean mayPlace(final ItemInstance item) {
        return false;
    }
    
    @Override
    public void onTake(final ItemInstance carried) {
        carried.onCraftedBy(this.player.level, this.player);
        if (carried.id == Items.ironIngot.id) this.player.awardStat(Achievements.acquireIron, 1);
        if (carried.id == Items.fish_cooked.id) this.player.awardStat(Achievements.cookFish, 1);
        super.onTake(carried);
    }
}
