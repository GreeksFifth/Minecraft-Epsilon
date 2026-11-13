// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item.crafting;

import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class WeaponRecipes
{
    private String[][] shapes = new String[][] {
            { "X",
              "X",
              "#" }
    };
    private Object[][] map = new Object[][] {
            { Block.wood, Block.stoneBrick, Items.ironIngot, Items.diamond, Items.goldIngot },
            { Items.sword_wood, Items.sword_stone, Items.sword_iron, Items.sword_diamond, Items.sword_gold }
    };
    
    public void addRecipes(final Recipes recipes) {
        for (int i = 0; i < this.map[0].length; ++i) {
            final Object o = this.map[0][i];
            for (int j = 0; j < this.map.length - 1; ++j) {
                recipes.addShapedRecipe(new ItemInstance((Item)this.map[j + 1][i]), this.shapes[j],
                        '#', Items.stick, 'X', o);
            }
        }
        recipes.addShapedRecipe(new ItemInstance(Items.bow, 1),
                " #X",
                "# X",
                " #X",

                'X', Items.string, '#', Items.stick);

        recipes.addShapedRecipe(new ItemInstance(Items.arrow, 4),
                "X",
                "#",
                "Y",

                'Y', Items.feather, 'X', Items.flint,
                '#', Items.stick);
    }
}
