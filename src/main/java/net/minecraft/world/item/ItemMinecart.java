// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.entity.item.EntityMinecart;
import net.minecraft.world.level.block.BlockRail;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.EntityPlayer;

public class ItemMinecart extends Item
{
    public int type;
    
    public ItemMinecart(final int id, final int type) {
        super(id);
        this.maxStackSize = 1;
        this.type = type;
    }
    
    @Override
    public boolean useOn(final ItemInstance itemInstance, final EntityPlayer player, final Level level, final int x, final int y, final int z, final int face) {
        int targetType = level.getTile(x, y, z);

        if (BlockRail.isRail(targetType)) {
            if (!level.isClientSide) {
                level.addEntity(new EntityMinecart(level, x + 0.5f, y + 0.5f, z + 0.5f, this.type));
            }
            itemInstance.count--;
            return true;
        }
        return false;
    }
}
