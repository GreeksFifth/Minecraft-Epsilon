// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item.crafting;

import net.minecraft.world.item.ItemDyePowder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.level.block.Blocks;

public class FoodRecipes
{
    public void addRecipes(final Recipes recipes) {
        recipes.addShapedRecipe(new ItemInstance(Items.mushroomStew),
                "Y",
                "X",
                "#",

                'X', Blocks.mushroom1,
                'Y', Blocks.mushroom2,
                '#', Items.bowl);

        recipes.addShapedRecipe(new ItemInstance(Items.mushroomStew),
                "Y",
                "X",
                "#",

                'X', Blocks.mushroom2,
                'Y', Blocks.mushroom1,
                '#', Items.bowl);

        recipes.addShapedRecipe(new ItemInstance(Items.cookie, 8),
                "#X#",

                'X', new ItemInstance(Items.dye_powder, 1, ItemDyePowder.BROWN),
                '#', Items.wheat);
    }
}
