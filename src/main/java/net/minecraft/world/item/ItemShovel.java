// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ItemShovel extends ItemSpecialMining
{
    private static Block[] diggables = new Block[] {
            Blocks.dirtGrassy,
            Blocks.dirt,
            Blocks.sand,
            Blocks.gravel,
            Blocks.snowLayer,
            Blocks.snow,
            Blocks.clay,
            Blocks.farmland,
            Blocks.soulSand
    };
    
    public ItemShovel(final int id, final Tier tier) {
        super(id, 1, tier, ItemShovel.diggables);
    }
    
    @Override
    public boolean canDestroySpecial(final Block block) {
        if (block == Blocks.snowLayer) return true;
        if (block == Blocks.snow) return true;
        return false;
    }

}
