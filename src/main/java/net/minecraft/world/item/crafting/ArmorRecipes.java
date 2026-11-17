// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item.crafting;

import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

public class ArmorRecipes
{
    private String[][] shapes = new String[][]
            {
                { "XXX",
                  "X X" },

                { "X X",
                  "XXX",
                  "XXX" },

                { "XXX",
                  "X X",
                  "X X" },

                { "X X",
                  "X X" }
            };
    private Object[][] map = new Object[][]
            {
                    { Items.leather, Blocks.fire, Items.ingotIron, Items.diamond, Items.ingotGold},
                    { Items.helmetLeather, Items.helmetChainmail, Items.helmetIron, Items.helmetDiamond, Items.helmetGold},
                    { Items.chestplateLeather, Items.chestplateChainmail, Items.chestplateIron, Items.chestplateDiamond, Items.chestplateGold},
                    { Items.leggingsLeather, Items.leggingsChainmail, Items.leggingsIron, Items.leggingsDiamond, Items.leggingsGold},
                    { Items.bootsLeather, Items.bootsChainmail, Items.bootsIron, Items.bootsDiamond, Items.bootsGold}
            };
    
    public void addRecipes(final Recipes recipes) {
        for (int m = 0; m < this.map[0].length; ++m) {
            final Object objMaterial = this.map[0][m];
            for (int t = 0; t < this.map.length - 1; ++t) {
                recipes.addShapedRecipe(new ItemInstance((Item)this.map[t + 1][m]), this.shapes[t], 'X', objMaterial);
            }
        }
    }
}
