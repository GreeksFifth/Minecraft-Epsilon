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
            { Blocks.woodenPlanks, Blocks.cobblestone, Items.ingotIron, Items.diamond, Items.ingotGold},
            { Items.pickaxeWood, Items.pickaxeStone, Items.pickaxeIron, Items.pickaxeDiamond, Items.pickaxeGold},
            { Items.shovelWood, Items.shovelStone, Items.shovelIron, Items.shovelDiamond, Items.shovelGold},
            { Items.axeWood, Items.axeStone, Items.axeIron, Items.axeDiamond, Items.axeGold},
            { Items.hoeWood, Items.hoeStone, Items.hoeIron, Items.hoeDiamond, Items.hoeGold}
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

                '#', Items.ingotIron);
    }
}
