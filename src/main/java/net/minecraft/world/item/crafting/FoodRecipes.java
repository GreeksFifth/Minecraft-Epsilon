// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item.crafting;

import net.minecraft.world.item.DyePowderItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ItemInstance;

public class FoodRecipes
{
    public void addRecipes(final Recipes recipes) {
        recipes.addShapedRecipe(new ItemInstance(Items.mushroomStew),
                "Y",
                "X",
                "#",

                'X', Block.mushroom1,
                'Y', Block.mushroom2,
                '#', Items.bowl);

        recipes.addShapedRecipe(new ItemInstance(Items.mushroomStew),
                "Y",
                "X",
                "#",

                'X', Block.mushroom2,
                'Y', Block.mushroom1,
                '#', Items.bowl);

        recipes.addShapedRecipe(new ItemInstance(Items.cookie, 8),
                "#X#",

                'X', new ItemInstance(Items.dye_powder, 1, DyePowderItem.BROWN),
                '#', Items.wheat);
    }
}
