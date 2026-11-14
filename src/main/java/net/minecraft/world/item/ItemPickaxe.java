// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.Block;

public class ItemPickaxe extends ItemSpecialMining
{
    private static Block[] diggables = new Block[] {
            Blocks.stoneBrick,
            Blocks.stoneSlab,
            Blocks.stoneSlabHalf,
            Blocks.rock,
            Blocks.sandStone,
            Blocks.mossStone,
            Blocks.ironOre,
            Blocks.ironBlock,
            Blocks.coalOre,
            Blocks.goldBlock,
            Blocks.goldOre,
            Blocks.diamondOre,
            Blocks.diamondBlock,
            Blocks.ice,
            Blocks.hellRock,
            Blocks.lapisOre,
            Blocks.lapisBlock,
            Blocks.dispenser,
            Blocks.furnace,
            Blocks.furnace_lit,
    };
    
    protected ItemPickaxe(final int id, final Tier tier) {
        super(id, 2, tier, ItemPickaxe.diggables);
    }
    
    @Override
    public boolean canDestroySpecial(final Block block) {
        if (block == Blocks.obsidian) return this.tier.getLevel() == 3;
        if (block == Blocks.diamondBlock || block == Blocks.diamondOre) return this.tier.getLevel() >= 2;
        if (block == Blocks.goldBlock || block == Blocks.goldOre) return this.tier.getLevel() >= 2;
        if (block == Blocks.ironBlock || block == Blocks.ironOre) return this.tier.getLevel() >= 1;
        if (block == Blocks.lapisBlock || block == Blocks.lapisOre) return this.tier.getLevel() >= 1;
        if (block == Blocks.redStoneOre || block == Blocks.redStoneOre_lit) return this.tier.getLevel() >= 2;
        if (block.material == Material.stone) return true;
        if (block.material == Material.metal) return true;
        return false;
    }

}
