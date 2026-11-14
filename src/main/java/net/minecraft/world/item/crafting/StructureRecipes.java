// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item.crafting;

import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.level.block.Blocks;

public class StructureRecipes
{
    public void addRecipes(final Recipes recipes) {
        recipes.addShapedRecipe(new ItemInstance(Blocks.chest),
                "###",
                "# #",
                "###",

                '#', Blocks.wood);

        recipes.addShapedRecipe(new ItemInstance(Blocks.furnace),
                "###",
                "# #",
                "###",

                '#', Blocks.stoneBrick);

        recipes.addShapedRecipe(new ItemInstance(Blocks.workBench),
                "##",
                "##",

                '#', Blocks.wood);

        recipes.addShapedRecipe(new ItemInstance(Blocks.sandStone),
                "##",
                "##",

                '#', Blocks.sand);
    }
}
