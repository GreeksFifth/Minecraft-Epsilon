// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item.crafting;

import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.level.block.Block;

public class StructureRecipes
{
    public void addRecipes(final Recipes recipes) {
        recipes.addShapedRecipe(new ItemInstance(Block.chest),
                "###",
                "# #",
                "###",

                '#', Block.wood);

        recipes.addShapedRecipe(new ItemInstance(Block.furnace),
                "###",
                "# #",
                "###",

                '#', Block.stoneBrick);

        recipes.addShapedRecipe(new ItemInstance(Block.workBench),
                "##",
                "##",

                '#', Block.wood);

        recipes.addShapedRecipe(new ItemInstance(Block.sandStone),
                "##",
                "##",

                '#', Block.sand);
    }
}
