// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.level.block.Blocks;

public class ItemHoe extends Item
{
    public ItemHoe(final int id, final Tier tier) {
        super(id);
        this.maxStackSize = 1;
        this.setMaxDamage(tier.getUses());
    }
    
    @Override
    public boolean useOn(final ItemInstance itemInstance, final EntityPlayer player, final Level level, final int x, final int y, final int z, final int face) {
        final int targetType = level.getTile(x, y, z);
        final int above = level.getTile(x, y + 1, z);

        if ((face != 0 && above == 0 && targetType == Blocks.grass.id) || targetType == Blocks.dirt.id) {
            final Block block = Blocks.farmland;
            level.playSound(x + 0.5f, y + 0.5f, z + 0.5f, block.soundType.getStepSound(), (block.soundType.getVolume() + 1.0f) / 2.0f, block.soundType.getPitch() * 0.8f);

            if (level.isClientSide) return true;
            level.setTile(x, y, z, block.id);
            itemInstance.hurt(1, player);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isHandEquipped() {
        return true;
    }
}
