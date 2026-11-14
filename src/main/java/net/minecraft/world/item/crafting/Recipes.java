// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item.crafting;

import net.minecraft.world.inventory.CraftingContainer;
import java.util.HashMap;
import java.util.Collections;

import net.minecraft.world.item.ItemCoal;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BlockSlab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class Recipes
{
    public static final int ANY_AUX_VALUE = -1;
    private static final Recipes instance = new Recipes();
    private List<Recipe> recipes = new ArrayList<>();
    
    public static final Recipes getInstance() {
        return Recipes.instance;
    }
    
    private Recipes() {
        new ToolRecipes().addRecipes(this);
        new WeaponRecipes().addRecipes(this);
        new OreRecipes().addRecipes(this);
        new FoodRecipes().addRecipes(this);
        new StructureRecipes().addRecipes(this);
        new ArmorRecipes().addRecipes(this);
        new ClothDyeRecipes().addRecipes(this);

        this.addShapedRecipe(new ItemInstance(Items.paper, 3),
                "###",

                '#', Items.reeds);

        this.addShapedRecipe(new ItemInstance(Items.book, 1),
                "#",
                "#",
                "#",

                '#', Items.paper);

        this.addShapedRecipe(new ItemInstance(Blocks.fence, 2),
                "###",
                "###",

                '#', Items.stick);

        this.addShapedRecipe(new ItemInstance(Blocks.recordPlayer, 1),
                "###",
                "#X#",
                "###",

                '#', Blocks.wood, 'X', Items.diamond);

        this.addShapedRecipe(new ItemInstance(Blocks.musicBlock, 1),
                "###",
                "#X#",
                "###",

                '#', Blocks.wood, 'X', Items.redStone);

        this.addShapedRecipe(new ItemInstance(Blocks.bookshelf, 1),
                "###",
                "XXX",
                "###",

                '#', Blocks.wood, 'X', Items.book);

        this.addShapedRecipe(new ItemInstance(Blocks.snow, 1),
                "##",
                "##",

                '#', Items.snowBall);

        this.addShapedRecipe(new ItemInstance(Blocks.clay, 1),
                "##",
                "##",

                '#', Items.clay);

        this.addShapedRecipe(new ItemInstance(Blocks.redBrick, 1),
                "##",
                "##",

                '#', Items.brick);

        this.addShapedRecipe(new ItemInstance(Blocks.lightGem, 1),
                "##",
                "##",

                '#', Items.yellowDust);

        this.addShapedRecipe(new ItemInstance(Blocks.cloth, 1),
                "##",
                "##",

                '#', Items.string);

        this.addShapedRecipe(new ItemInstance(Blocks.tnt, 1),
                "X#X",
                "#X#",
                "X#X",

                'X', Items.sulphur, '#', Blocks.sand);

        this.addShapedRecipe(new ItemInstance(Blocks.stoneSlabHalf, 3, BlockSlab.COBBLESTONE_SLAB),
                "###",

                '#', Blocks.stoneBrick);

        this.addShapedRecipe(new ItemInstance(Blocks.stoneSlabHalf, 3, BlockSlab.STONE_SLAB),
                "###",

                '#', Blocks.rock);

        this.addShapedRecipe(new ItemInstance(Blocks.stoneSlabHalf, 3, BlockSlab.SAND_SLAB),
                "###",

                '#', Blocks.sandStone);

        this.addShapedRecipe(new ItemInstance(Blocks.stoneSlabHalf, 3, BlockSlab.WOOD_SLAB),
                "###",

                '#', Blocks.wood);

        this.addShapedRecipe(new ItemInstance(Blocks.ladder, 2),
                "# #",
                "###",
                "# #",

                '#', Items.stick);

        this.addShapedRecipe(new ItemInstance(Items.door_wood, 1),
                "##",
                "##",
                "##",

                '#', Blocks.wood);

        this.addShapedRecipe(new ItemInstance(Blocks.trapdoor, 2),
                "###",
                "###",

                '#', Blocks.wood);

        this.addShapedRecipe(new ItemInstance(Items.door_iron, 1),
                "##",
                "##",
                "##",

                '#', Items.ironIngot);

        this.addShapedRecipe(new ItemInstance(Items.sign, 1),
                "###",
                "###",
                " X ",

                '#', Blocks.wood,
                'X', Items.stick);

        this.addShapedRecipe(new ItemInstance(Items.cake, 1),
                "AAA",
                "BEB",
                "CCC",

                'A', Items.milk, 'B', Items.sugar,
                'C', Items.wheat, 'E', Items.egg);

        this.addShapedRecipe(new ItemInstance(Items.sugar, 1),
                "#",

                '#', Items.reeds);

        this.addShapedRecipe(new ItemInstance(Blocks.wood, 4),
                "#",

                '#', Blocks.treeTrunk);

        this.addShapedRecipe(new ItemInstance(Items.stick, 4),
                "#",
                "#",

                '#', Blocks.wood);

        this.addShapedRecipe(new ItemInstance(Blocks.torch, 4),
                "X",
                "#",

                'X', Items.coal, '#', Items.stick);

        this.addShapedRecipe(new ItemInstance(Blocks.torch, 4),
                "X",
                "#",

                'X', new ItemInstance(Items.coal, 1, ItemCoal.CHAR_COAL),
                '#', Items.stick);

        this.addShapedRecipe(new ItemInstance(Items.bowl, 4),
                "# #",
                " # ",

                '#', Blocks.wood);

        this.addShapedRecipe(new ItemInstance(Blocks.rail, 16),
                "X X",
                "X#X",
                "X X",

                'X', Items.ironIngot, '#', Items.stick);

        this.addShapedRecipe(new ItemInstance(Blocks.goldenRail, 6),
                "X X",
                "X#X",
                "XRX",

                'X', Items.goldIngot, 'R', Items.redStone,
                '#', Items.stick);

        this.addShapedRecipe(new ItemInstance(Blocks.detectorRail, 6),
                "X X",
                "X#X",
                "XRX",

                'X', Items.ironIngot, 'R', Items.redStone,
                '#', Blocks.pressurePlate_stone);

        this.addShapedRecipe(new ItemInstance(Items.minecart, 1),
                "# #",
                "###",

                '#', Items.ironIngot);

        this.addShapedRecipe(new ItemInstance(Blocks.litPumpkin, 1),
                "A",
                "B",

                'A', Blocks.pumpkin, 'B', Blocks.torch);

        this.addShapedRecipe(new ItemInstance(Items.minecart_chest, 1),
                "A",
                "B",

                'A', Blocks.chest, 'B', Items.minecart);

        this.addShapedRecipe(new ItemInstance(Items.minecart_furnace, 1),
                "A",
                "B",

                'A', Blocks.furnace, 'B', Items.minecart);

        this.addShapedRecipe(new ItemInstance(Items.boat, 1),
                "# #",
                "###",

                '#', Blocks.wood);

        this.addShapedRecipe(new ItemInstance(Items.bucket_empty, 1),
                "# #",
                " # ",

                '#', Items.ironIngot);

        this.addShapedRecipe(new ItemInstance(Items.flintAndSteel, 1),
                "A ",
                " B",

                'A', Items.ironIngot, 'B', Items.flint);

        this.addShapedRecipe(new ItemInstance(Items.bread, 1),
                "###",

                '#', Items.wheat);

        this.addShapedRecipe(new ItemInstance(Blocks.stairs_wood, 4),
                "#  ",
                "## ",
                "###",

                '#', Blocks.wood);

        this.addShapedRecipe(new ItemInstance(Items.fishingRod, 1),
                "  #",
                " #X",
                "# X",

                '#', Items.stick, 'X', Items.string);

        this.addShapedRecipe(new ItemInstance(Blocks.stairs_stone, 4),
                "#  ",
                "## ",
                "###",

                '#', Blocks.stoneBrick);

        this.addShapedRecipe(new ItemInstance(Items.painting, 1),
                "###",
                "#X#",
                "###",

                '#', Items.stick, 'X', Blocks.cloth);

        this.addShapedRecipe(new ItemInstance(Items.apple_gold, 1),
                "###",
                "#X#",
                "###",

                '#', Blocks.goldBlock, 'X', Items.apple);

        this.addShapedRecipe(new ItemInstance(Blocks.lever, 1),
                "X",
                "#",

                '#', Blocks.stoneBrick, 'X', Items.stick);

        this.addShapedRecipe(new ItemInstance(Blocks.notGate_on, 1),
                "X",
                "#",

                '#', Items.stick, 'X', Items.redStone);

        this.addShapedRecipe(new ItemInstance(Items.diode, 1),
                "#X#",
                "III",

                '#', Blocks.notGate_on, 'X', Items.redStone,
                'I', Blocks.rock);

        this.addShapedRecipe(new ItemInstance(Items.clock, 1),
                " # ",
                "#X#",
                " # ",

                '#', Items.goldIngot, 'X', Items.redStone);

        this.addShapedRecipe(new ItemInstance(Items.compass, 1),
                " # ",
                "#X#",
                " # ",

                '#', Items.ironIngot, 'X', Items.redStone);

        this.addShapedRecipe(new ItemInstance(Items.map, 1),
                "###",
                "#X#",
                "###",

                '#', Items.paper, 'X', Items.compass);

        this.addShapedRecipe(new ItemInstance(Blocks.button, 1),
                "#",
                "#",

                '#', Blocks.rock);

        this.addShapedRecipe(new ItemInstance(Blocks.pressurePlate_stone, 1),
                "##",

                '#', Blocks.rock);

        this.addShapedRecipe(new ItemInstance(Blocks.pressurePlate_wood, 1),
                "##",

                '#', Blocks.wood);

        this.addShapedRecipe(new ItemInstance(Blocks.dispenser, 1),
                "###",
                "#X#",
                "#R#",

                '#', Blocks.stoneBrick, 'X', Items.bow,
                'R', Items.redStone);

        this.addShapedRecipe(new ItemInstance(Blocks.pistonBase, 1),
                "TTT",
                "#X#",
                "#R#",

                '#', Blocks.stoneBrick, 'X', Items.ironIngot,
                'R', Items.redStone, 'T', Blocks.wood);

        this.addShapedRecipe(new ItemInstance(Blocks.pistonStickyBase, 1),
                "S",
                "P",

                'S', Items.slimeBall, 'P', Blocks.pistonBase);

        this.addShapedRecipe(new ItemInstance(Items.bed, 1),
                "###",
                "XXX",

                '#', Blocks.cloth, 'X', Blocks.wood);

        Collections.sort(this.recipes, (r0, r1) -> {
            if (r0 instanceof RecipeShapeless && r1 instanceof RecipeShaped) return 1;
            if (r1 instanceof RecipeShapeless && r0 instanceof RecipeShaped) return -1;

            if (r1.size() < r0.size()) return -1;
            if (r1.size() > r0.size()) return 1;
            return 0;
        });
        System.out.println(this.recipes.size() + " recipes");
    }

    void addShapedRecipe(final ItemInstance result, final Object... args) {
        String map = "";
        int p = 0;
        int width = 0;
        int height = 0;

        if (args[p] instanceof String[]) {
            final String[] array = (String[])args[p++];

            for (int i = 0; i < array.length; ++i) {
                final String s = array[i];
                height++;
                width = s.length();
                map += s;
            }
        }
        else {
            while (args[p] instanceof String) {
                final String s = (String)args[p++];
                ++height;
                width = s.length();
                map += s;
            }
        }

        final HashMap<Character, Object> mappings = new HashMap<>();

        while (p < args.length) {
            final Character from = (Character)args[p];
            Object to = null;
            if (args[p + 1] instanceof Item) {
                to = new ItemInstance((Item)args[p + 1]);
            }
            else if (args[p + 1] instanceof Block) {
                to = new ItemInstance((Block)args[p + 1], 1, -1);
            }
            else if (args[p + 1] instanceof ItemInstance) {
                to = args[p + 1];
            }

            mappings.put(from, to);
            p += 2;
        }

        final ItemInstance[] ids = new ItemInstance[width * height];

        for (int j = 0; j < width * height; ++j) {
            final char char1 = map.charAt(j);
            if (mappings.containsKey(char1)) {
                ids[j] = ((ItemInstance)mappings.get(char1)).copy();
            }
            else {
                ids[j] = null;
            }
        }

        RecipeShaped recipe = new RecipeShaped(width, height, ids, result);
        this.recipes.add(recipe);
    }
    
    void addShapelessRecipe(final ItemInstance result, final Object... args) {
        final ArrayList<ItemInstance> ingredients = new ArrayList<>();
        for (final Object o : args) {
            if (o instanceof ItemInstance) {
                ingredients.add(((ItemInstance)o).copy());
            } else if (o instanceof Item) {
                ingredients.add(new ItemInstance((Item) o));
            } else if (o instanceof Block) {
                ingredients.add(new ItemInstance((Block) o));
            } else {
                throw new RuntimeException("Invalid shapeless recipy!");
            }
        }

        this.recipes.add(new RecipeShapeless(result, ingredients));
    }
    
    public ItemInstance getItemFor(final CraftingContainer craftSlots) {
        for (int i = 0; i < this.recipes.size(); ++i) {
            final Recipe recipe = this.recipes.get(i);
            if (recipe.matches(craftSlots)) {
                return recipe.assemble(craftSlots);
            }
        }
        return null;
    }
    
    public List<Recipe> getRecipes() {
        return this.recipes;
    }

}
