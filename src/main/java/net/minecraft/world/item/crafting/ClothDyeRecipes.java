// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item.crafting;

import net.minecraft.world.item.ItemDyePowder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BlockWool;
import net.minecraft.world.level.block.Blocks;

public class ClothDyeRecipes
{
    public void addRecipes(final Recipes recipes) {
        for (int i = 0; i < 16; ++i) {
            recipes.addShapelessRecipe(new ItemInstance(Blocks.cloth, 1, BlockWool.getItemAuxValueForTileData(i)),
                    new ItemInstance(Items.dye_powder, 1, i),
                    new ItemInstance(Item.items[Blocks.cloth.id], 1, 15 - ItemDyePowder.WHITE));
        }

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 2, ItemDyePowder.YELLOW),
                Blocks.flower);

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 2, ItemDyePowder.RED),
                Blocks.rose);

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 3, ItemDyePowder.WHITE),
                Items.bone);

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 2, ItemDyePowder.PINK),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.RED),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.WHITE));

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 2, ItemDyePowder.ORANGE),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.RED),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.YELLOW));

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 2, ItemDyePowder.LIME),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.GREEN),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.WHITE));

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 2, ItemDyePowder.GRAY),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.BLACK),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.WHITE));

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 2, ItemDyePowder.SILVER),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.GRAY),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.WHITE));

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 3, ItemDyePowder.SILVER),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.BLACK),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.WHITE),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.WHITE));

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 2, ItemDyePowder.LIGHT_BLUE),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.BLUE),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.WHITE));

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 2, ItemDyePowder.CYAN),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.BLUE),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.GREEN));

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 2, ItemDyePowder.PURPLE),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.BLUE),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.RED));

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 2, ItemDyePowder.MAGENTA),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.PURPLE),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.PINK));

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 3, ItemDyePowder.MAGENTA),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.BLUE),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.RED),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.PINK));

        recipes.addShapelessRecipe(new ItemInstance(Items.dye_powder, 4, ItemDyePowder.MAGENTA),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.BLUE),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.RED),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.RED),
                new ItemInstance(Items.dye_powder, 1, ItemDyePowder.WHITE));
    }
}
