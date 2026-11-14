// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ItemAxe extends ItemSpecialMining
{
    private static Block[] diggables = new Block[] {
            Blocks.wood,
            Blocks.bookshelf,
            Blocks.treeTrunk,
            Blocks.chest
    };
    
    protected ItemAxe(final int id, final Tier tier) {
        super(id, 3, tier, ItemAxe.diggables);
    }

}
