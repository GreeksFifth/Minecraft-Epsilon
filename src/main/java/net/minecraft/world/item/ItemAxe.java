// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ItemAxe extends ItemSpecialMining
{
    private static Block[] diggables = new Block[] {
            Blocks.woodenPlanks,
            Blocks.log,
            Blocks.workBench, // Argent - new
            Blocks.chest,
            Blocks.bookshelf,
            Blocks.noteblock, // Argent - new
            Blocks.jukebox, // Argent - new
            Blocks.stairs_wood // Argent - new
    };
    
    protected ItemAxe(final int id, final Tier tier) {
        super(id, 3, tier, ItemAxe.diggables);
    }

}
