// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item.crafting;

import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class ToolRecipes
{
    private String[][] shapes = new String[][] {
            { "XXX",
              " # ",
              " # " },

            { "X",
              "#",
              "#" },

            { "XX",
              "X#",
              " #" },

            { "XX",
              " #",
              " #" } };
    private Object[][] map = new Object[][] {
            { Blocks.wood, Blocks.stoneBrick, Items.ironIngot, Items.diamond, Items.goldIngot },
            { Items.pickAxe_wood, Items.pickAxe_stone, Items.pickAxe_iron, Items.pickAxe_diamond, Items.pickAxe_gold },
            { Items.shovel_wood, Items.shovel_stone, Items.shovel_iron, Items.shovel_diamond, Items.shovel_gold },
            { Items.hatchet_wood, Items.hatchet_stone, Items.hatchet_iron, Items.hatchet_diamond, Items.hatchet_gold },
            { Items.hoe_wood, Items.hoe_stone, Items.hoe_iron, Items.hoe_diamond, Items.hoe_gold }
    };
    
    public void addRecipes(final Recipes recipes) {
        for (int m = 0; m < this.map[0].length; ++m) {
            final Object objMaterial = this.map[0][m];

            for (int t = 0; t < this.map.length - 1; ++t) {
                recipes.addShapedRecipe(new ItemInstance((Item)this.map[t + 1][m]), this.shapes[t],
                        '#', Items.stick, 'X', objMaterial);
            }
        }

        recipes.addShapedRecipe(new ItemInstance(Items.shears),
                " #",
                "# ",

                '#', Items.ironIngot);
    }
}
