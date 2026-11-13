// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item.crafting;

import net.minecraft.world.item.DyePowderItem;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class OreRecipes
{
    private Object[][] map = new Object[][]
            {
                    { Block.goldBlock, new ItemInstance(Items.goldIngot, 9) },
                    { Block.ironBlock, new ItemInstance(Items.ironIngot, 9) },
                    { Block.diamondBlock, new ItemInstance(Items.diamond, 9) },
                    { Block.lapisBlock, new ItemInstance(Items.dye_powder, 9, DyePowderItem.BLUE) }
            };
    
    public void addRecipes(final Recipes recipes) {
        for (int i = 0; i < this.map.length; ++i) {
            final Block block = (Block)this.map[i][0];
            final ItemInstance result = (ItemInstance)this.map[i][1];
            recipes.addShapedRecipe(new ItemInstance(block),
                    "###",
                    "###",
                    "###",

                    '#', result);
            recipes.addShapedRecipe(result,
                    "#",

                    '#', block);
        }
    }
}
