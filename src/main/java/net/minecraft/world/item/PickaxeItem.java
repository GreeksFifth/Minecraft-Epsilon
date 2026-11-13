// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.Block;

public class PickaxeItem extends DiggerItem
{
    private static Block[] diggables = new Block[] {
            Block.stoneBrick,
            Block.stoneSlab,
            Block.stoneSlabHalf,
            Block.rock,
            Block.sandStone,
            Block.mossStone,
            Block.ironOre,
            Block.ironBlock,
            Block.coalOre,
            Block.goldBlock,
            Block.goldOre,
            Block.diamondOre,
            Block.diamondBlock,
            Block.ice,
            Block.hellRock,
            Block.lapisOre,
            Block.lapisBlock };
    
    protected PickaxeItem(final int id, final Tier tier) {
        super(id, 2, tier, PickaxeItem.diggables);
    }
    
    @Override
    public boolean canDestroySpecial(final Block block) {
        if (block == Block.obsidian) return this.tier.getLevel() == 3;
        if (block == Block.diamondBlock || block == Block.diamondOre) return this.tier.getLevel() >= 2;
        if (block == Block.goldBlock || block == Block.goldOre) return this.tier.getLevel() >= 2;
        if (block == Block.ironBlock || block == Block.ironOre) return this.tier.getLevel() >= 1;
        if (block == Block.lapisBlock || block == Block.lapisOre) return this.tier.getLevel() >= 1;
        if (block == Block.redStoneOre || block == Block.redStoneOre_lit) return this.tier.getLevel() >= 2;
        if (block.material == Material.stone) return true;
        if (block.material == Material.metal) return true;
        return false;
    }

}
