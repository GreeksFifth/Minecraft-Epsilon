// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item.crafting;

import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;

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
                    { Items.leather, Block.fire, Items.ironIngot, Items.diamond, Items.goldIngot },
                    { Items.helmet_cloth, Items.helmet_chain, Items.helmet_iron, Items.helmet_diamond, Items.helmet_gold },
                    { Items.chestplate_cloth, Items.chestplate_chain, Items.chestplate_iron, Items.chestplate_diamond, Items.chestplate_gold },
                    { Items.leggings_cloth, Items.leggings_chain, Items.leggings_iron, Items.leggings_diamond, Items.leggings_gold },
                    { Items.boots_cloth, Items.boots_chain, Items.boots_iron, Items.boots_diamond, Items.boots_gold }
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
