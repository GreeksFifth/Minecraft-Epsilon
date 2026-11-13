// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.tile.Block;
import net.minecraft.world.entity.Mob;

public class ShearsItem extends Item
{
    public ShearsItem(final int id) {
        super(id);
        this.setMaxStackSize(1);
        this.setMaxDamage(238);
    }
    
    @Override
    public boolean mineBlock(final ItemInstance itemInstance, final int tile, final int x, final int y, final int z, final Mob owner) {
        if (tile == Block.leaves.id || tile == Block.web.id) {
            itemInstance.hurt(1, owner);
        }
        return super.mineBlock(itemInstance, tile, x, y, z, owner);
    }
    
    @Override
    public boolean canDestroySpecial(final Block block) {
        return block.id == Block.web.id;
    }
    
    @Override
    public float getDestroySpeed(final ItemInstance itemInstance, final Block block) {
        if (block.id == Block.web.id || block.id == Block.leaves.id) {
            return 15.0f;
        }
        if (block.id == Block.cloth.id) {
            return 5.0f;
        }
        return super.getDestroySpeed(itemInstance, block);
    }
}
