// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

public class ItemArmor extends Item
{
    public static final int SLOT_HEAD = 0;
    public static final int SLOT_TORSO = 1;
    public static final int SLOT_LEGS = 2;
    public static final int SLOT_FEET = 3;
    private static final int[] defensePerSlot = new int[] { 3, 8, 6, 3 };
    private static final int[] healthPerSlot = new int[] { 11, 16, 15, 13 };
    public final int tier;
    public final int slot;
    public final int defense;
    public final int materialIcon;
    
    public ItemArmor(final int id, final int tier, final int materialIcon, final int slot) {
        super(id);
        this.tier = tier;
        this.slot = slot;
        this.materialIcon = materialIcon;
        this.defense = ItemArmor.defensePerSlot[slot];
        this.setMaxDamage(ItemArmor.healthPerSlot[slot] * 3 << tier);
        this.maxStackSize = 1;
    }

}
