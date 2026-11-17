// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item.crafting;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class FurnaceRecipes
{
    private static final FurnaceRecipes instance = new FurnaceRecipes();
    private Map<Integer, ItemInstance> recipes = new HashMap<>();
    
    public static final FurnaceRecipes getInstance() {
        return FurnaceRecipes.instance;
    }
    
    private FurnaceRecipes() {
        this.addFurnaceRecipe(Blocks.ironOre.id, new ItemInstance(Items.ingotIron));
        this.addFurnaceRecipe(Blocks.goldOre.id, new ItemInstance(Items.ingotGold));
        this.addFurnaceRecipe(Blocks.diamondOre.id, new ItemInstance(Items.diamond));
        this.addFurnaceRecipe(Blocks.sand.id, new ItemInstance(Blocks.glass));
        this.addFurnaceRecipe(Items.porkChopRaw.id, new ItemInstance(Items.porkChopCooked));
        this.addFurnaceRecipe(Items.fishRaw.id, new ItemInstance(Items.fishCooked));
        this.addFurnaceRecipe(Blocks.cobblestone.id, new ItemInstance(Blocks.stone));
        this.addFurnaceRecipe(Items.clay.id, new ItemInstance(Items.brick));
        this.addFurnaceRecipe(Blocks.cactus.id, new ItemInstance(Items.dyePowder, 1, ItemDyePowder.GREEN));
        this.addFurnaceRecipe(Blocks.log.id, new ItemInstance(Items.coal, 1, ItemCoal.CHAR_COAL));
    }
    
    public void addFurnaceRecipe(final int itemId, final ItemInstance result) {
        this.recipes.put(itemId, result);
    }

    // Useless - In b1.2 and LCE leaks
    public boolean isFurnaceItem(int itemId) {
        return this.recipes.containsKey(itemId);
    }
    
    public ItemInstance getResult(final int itemId) {
        return this.recipes.get(itemId);
    }
    
    public Map<Integer, ItemInstance> getRecipes() {
        return this.recipes;
    }

}
