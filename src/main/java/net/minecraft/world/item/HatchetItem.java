// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.tile.Block;

public class HatchetItem extends DiggerItem
{
    private static Block[] diggables = new Block[] {
            Block.wood,
            Block.bookshelf,
            Block.treeTrunk,
            Block.chest
    };
    
    protected HatchetItem(final int id, final Tier tier) {
        super(id, 3, tier, HatchetItem.diggables);
    }

}
