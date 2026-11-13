// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.tile.Block;

public class ShovelItem extends DiggerItem
{
    private static Block[] diggables = new Block[] {
            Block.grass,
            Block.dirt,
            Block.sand,
            Block.gravel,
            Block.topSnow,
            Block.snow,
            Block.clay,
            Block.farmland };
    
    public ShovelItem(final int id, final Tier tier) {
        super(id, 1, tier, ShovelItem.diggables);
    }
    
    @Override
    public boolean canDestroySpecial(final Block block) {
        if (block == Block.topSnow) return true;
        if (block == Block.snow) return true;
        return false;
    }

}
