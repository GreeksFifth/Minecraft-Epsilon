// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.locale.Descriptive;
import net.minecraft.stats.Stats;
import net.minecraft.world.Container;
import net.minecraft.locale.language.I18n;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import java.util.Random;

public class Item implements Descriptive<Item> {
    public static final int ITEM_NUM_COUNT = 32000;
    protected static Random random = new Random();
    private static final int MAX_STACK_SIZE = Container.LARGE_MAX_STACK_SIZE;
    public static Item[] items = new Item[ITEM_NUM_COUNT];

    static {
        Stats.buildItemStats();
    }
    public final int id;
    protected int maxStackSize = Item.MAX_STACK_SIZE;
    private int maxDamage = 0;
    protected int icon;
    protected boolean handEquipped = false;
    protected boolean isStackedByData = false;
    private Item craftingRemainingItem = null;

    private String descriptionId;

    protected Item(final int id) {
        this.id = Block.TILE_NUM_COUNT + id;
        if (Item.items[Block.TILE_NUM_COUNT + id] != null) System.out.println("CONFLICT @ " + id);
        Item.items[Block.TILE_NUM_COUNT + id] = this;
    }

    public Item setIcon(final int icon) {
        this.icon = icon;
        return this;
    }

    public Item setMaxStackSize(final int maxStackSize) {
        this.maxStackSize = maxStackSize;
        return this;
    }

    public Item setIcon(final int x, final int y) {
        this.icon = x + y * 16;
        return this;
    }

    public int getIcon(final int auxValue) {
        return this.icon;
    }

    public final int getIcon(final ItemInstance itemInstance) {
        return this.getIcon(itemInstance.getAuxValue());
    }

    public boolean useOn(final ItemInstance itemInstance, final Player player, final Level level, final int x, final int y, final int z, final int face) {
        return false;
    }

    public float getDestroySpeed(final ItemInstance itemInstance, final Block block) {
        return 1.0f;
    }

    public ItemInstance use(final ItemInstance itemInstance, final Level level, final Player player) {
        return itemInstance;
    }

    public int getMaxStackSize() {
        return this.maxStackSize;
    }

    public int getLevelDataForAuxValue(final int auxValue) {
        return 0;
    }

    public boolean isStackedByData() {
        return this.isStackedByData;
    }

    protected Item setStackedByData(final boolean isStackedByData) {
        this.isStackedByData = isStackedByData;
        return this;
    }

    public int getMaxDamage() {
        return this.maxDamage;
    }

    protected Item setMaxDamage(final int maxDamage) {
        this.maxDamage = maxDamage;
        return this;
    }

    public boolean canBeDepleted() {
        return this.maxDamage > 0 && !this.isStackedByData;
    }


    /**
     * Returns true when the item was used to deal more than default damage
     *
     * @param itemInstance
     * @param mob
     * @param attacker
     * @return
     */
    public boolean hurtEnemy(final ItemInstance itemInstance, final Mob mob, final Mob attacker) {
        return false;
    }

    /**
     * Returns true when the item was used to mine more efficiently
     *
     * @param itemInstance
     * @param tile
     * @param x
     * @param y
     * @param z
     * @param owner
     * @return
     */
    public boolean mineBlock(final ItemInstance itemInstance, final int tile, final int x, final int y, final int z, final Mob owner) {
        return false;
    }

    public int getAttackDamage(final Entity entity) {
        return 1;
    }

    public boolean canDestroySpecial(final Block block) {
        return false;
    }

    public void interactEnemy(final ItemInstance itemInstance, final Mob mob) {
    }

    public Item handEquipped() {
        this.handEquipped = true;
        return this;
    }

    public boolean isHandEquipped() {
        return this.handEquipped;
    }

    public boolean isMirroredArt() {
        return false;
    }

    public Item setDescriptionId(final String id) {
        this.descriptionId = "item." + id;
        return this;
    }

    public String getDescriptionId() {
        return this.descriptionId;
    }

    public String getDescriptionId(final ItemInstance itemInstance) {
        return this.descriptionId;
    }

    public Item setCraftingRemainingItem(final Item craftingRemainingItem) {
        if (this.maxStackSize > 1) throw new IllegalArgumentException("Max stack size must be 1 for items with crafting results");
        this.craftingRemainingItem = craftingRemainingItem;
        return this;
    }

    public Item getCraftingRemainingItem() {
        return this.craftingRemainingItem;
    }

    public boolean hasCraftingRemainingItem() {
        return this.craftingRemainingItem != null;
    }

    public String getName() {
        return I18n.get(this.getDescriptionId() + ".name");
    }

    public int getColor(final int auxData) {
        return 0xffffff;
    }

    public void inventoryTick(final ItemInstance itemInstance, final Level level, final Entity owner, final int slot, final boolean selected) {
    }

    public void onCraftedBy(final ItemInstance itemInstance, final Level level, final Player player) {
    }

    public boolean isComplex() {
        return false;
    }

    public enum Tier
    {
        WOOD(0, 59, 2.0f, 0),
        STONE(1, 131, 4.0f, 1),
        IRON(2, 250, 6.0f, 2),
        DIAMOND(3, 1561, 8.0f, 3),
        GOLD(0, 32, 12.0f, 0);

        private final int level;
        private final int uses;
        private final float speed;
        private final int damage;

        Tier(final int level, final int uses, final float speed, final int damage) {
            this.level = level;
            this.uses = uses;
            this.speed = speed;
            this.damage = damage;
        }

        public int getUses() {
            return this.uses;
        }

        public float getSpeed() {
            return this.speed;
        }

        public int getAttackDamageBonus() {
            return this.damage;
        }

        public int getLevel() {
            return this.level;
        }
    }
}
