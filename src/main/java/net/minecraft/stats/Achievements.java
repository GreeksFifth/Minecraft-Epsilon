// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.stats;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Achievements
{
    protected static final int ACHIEVEMENT_OFFSET = 0x500000;
    public static final int ACHIEVEMENT_WIDTH_POSITION = 12;
    public static final int ACHIEVEMENT_HEIGHT_POSITION = 12;

    public static int xMin, yMin, xMax, yMax;

    public static List<Achievement> achievements = new ArrayList<>();

    public static Achievement openInventory = new Achievement(0, "openInventory", 0, 0, Items.book, null).setAwardLocallyOnly().postConstruct();
    public static Achievement mineWood = new Achievement(1, "mineWood", 2, 1, Block.treeTrunk, Achievements.openInventory).postConstruct();
    public static Achievement buildWorkbench = new Achievement(2, "buildWorkBench", 4, -1, Block.workBench, Achievements.mineWood).postConstruct();
    public static Achievement buildPickaxe = new Achievement(3, "buildPickaxe", 4, 2, Items.pickAxe_wood, Achievements.buildWorkbench).postConstruct();
    public static Achievement buildFurnace = new Achievement(4, "buildFurnace", 3, 4, Block.furnace_lit, Achievements.buildPickaxe).postConstruct();
    public static Achievement acquireIron = new Achievement(5, "acquireIron", 1, 4, Items.ironIngot, Achievements.buildFurnace).postConstruct();
    public static Achievement buildHoe = new Achievement(6, "buildHoe", 2, -3, Items.hoe_wood, Achievements.buildWorkbench).postConstruct();
    public static Achievement makeBread = new Achievement(7, "makeBread", -1, -3, Items.bread, Achievements.buildHoe).postConstruct();
    public static Achievement bakeCake = new Achievement(8, "bakeCake", 0, -5, Items.cake, Achievements.buildHoe).postConstruct();
    public static Achievement buildBetterPickaxe = new Achievement(9, "buildBetterPickaxe", 6, 2, Items.pickAxe_stone, Achievements.buildPickaxe).postConstruct();
    public static Achievement cookFish = new Achievement(10, "cookFish", 2, 6, Items.fish_cooked, Achievements.buildFurnace).postConstruct();
    public static Achievement onARail = new Achievement(11, "onARail", 2, 3, Block.rail, Achievements.acquireIron).setGolden().postConstruct();
    public static Achievement buildSword = new Achievement(12, "buildSword", 6, -1, Items.sword_wood, Achievements.buildWorkbench).postConstruct();
    public static Achievement killEnemy = new Achievement(13, "killEnemy", 8, -1, Items.bone, Achievements.buildSword).postConstruct();
    public static Achievement killCow = new Achievement(14, "killCow", 7, -3, Items.leather, Achievements.buildSword).postConstruct();
    public static Achievement flyPig = new Achievement(15, "flyPig", 8, -4, Items.saddle, Achievements.killCow).setGolden().postConstruct();

    static {
        System.out.println(Achievements.achievements.size() + " achievements");
    }

    public static void init() {
    }
}
