// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.EntityMob;
import net.minecraft.world.level.block.Blocks;

public class ItemShears extends Item
{
    public ItemShears(final int id) {
        super(id);
        this.setMaxStackSize(1);
        this.setMaxDamage(238);
    }
    
    @Override
    public boolean mineBlock(final ItemInstance itemInstance, final int tile, final int x, final int y, final int z, final EntityMob owner) {
        if (tile == Blocks.leaves.id || tile == Blocks.web.id) {
            itemInstance.hurt(1, owner);
        }
        return super.mineBlock(itemInstance, tile, x, y, z, owner);
    }
    
    @Override
    public boolean canDestroySpecial(final Block block) {
        return block.id == Blocks.web.id;
    }
    
    @Override
    public float getDestroySpeed(final ItemInstance itemInstance, final Block block) {
        if (block.id == Blocks.web.id || block.id == Blocks.leaves.id) {
            return 15.0f;
        }
        if (block.id == Blocks.cloth.id) {
            return 5.0f;
        }
        return super.getDestroySpeed(itemInstance, block);
    }
}
