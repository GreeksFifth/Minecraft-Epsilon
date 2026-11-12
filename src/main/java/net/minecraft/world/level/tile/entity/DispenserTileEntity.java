// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.tile.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import com.mojang.nbt.ListTag;
import com.mojang.nbt.CompoundTag;
import java.util.Random;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.Container;

public class DispenserTileEntity extends TileEntity implements Container
{
    private ItemInstance[] items = new ItemInstance[9];
    // private Random random = new Random();
    Minecraft mc = Minecraft.instance;
    private int targetSlot = 0; // Argent - specifically used for dispenser activation
    private boolean empty = true;

    public int getContainerSize() {
        return 9;
    }
    
    public ItemInstance getItem(final int slot) {
        return this.items[slot];
    }
    
    public ItemInstance removeItem(final int slot, final int count) {
        if (slot < 0 || slot > 8){
            // System.out.println("Abnormal slot index of \""+slot+"\" detected, terminating method");
            return null; // Argent - Avoids crashes from attempting to use abnormal slot indexes
        }
        if (this.items[slot] != null) {
            if (this.items[slot].count <= count) {
                final ItemInstance item = this.items[slot];
                this.items[slot] = null;
                this.setChanged();
                if (slot == targetSlot && this.items[slot] == null) skipEmptySlots(slot);
                return item;
            } else {
                final ItemInstance i = this.items[slot].remove(count);
                if (this.items[slot].count == 0) this.items[slot] = null;
                this.setChanged();
                if (slot == targetSlot && this.items[slot] == null) skipEmptySlots(slot);
                return i;
            }
        }
        return null;
    }

    // Argent - Sequential slot iteration; only accessible within this method. Replaces "removeRandomItem".
    public ItemInstance removeSequentialItem() {
        targetSlot++;
        if (targetSlot > 8) {
            targetSlot = 0;
        }

        skipEmptySlots(targetSlot);

        return this.removeItem(targetSlot, 1);
    }

    public void skipEmptySlots(final int selected) {
        // Argent - To ensure empty slots are skipped in sequential order
        for(int i = 0; i < 9; ++i){
            if(this.items[targetSlot] == null){
                targetSlot++;
            } else {
                break; // Argent - An occupied slot has been found and will become the new target
            }
            if(targetSlot > 8){
                targetSlot = 0;
            }
            if (i == 8){
                empty = true; // Argent - mark as empty if the for() loop manages to loop 8 times
                targetSlot = -1;
            }
        }
    }

    public void setItem(final int slot, final ItemInstance item) {
        this.items[slot] = item;
        if (item != null && item.count > this.getMaxStackSize()) item.count = this.getMaxStackSize();
        this.setChanged();
        if (empty == true) { // T flip-flop; switching state requires external condition to be met
            targetSlot = slot;
            empty = false; // Argent - mark as not empty if any item is placed in dispenser
        }
    }
    
    public String getName() {
        return "Trap";
    }
    
    @Override
    public void load(final CompoundTag base) {
        super.load(base);
        final ListTag<CompoundTag> inventoryList = (ListTag<CompoundTag>) base.getList("Items");
        this.items = new ItemInstance[this.getContainerSize()];
        for (int i = 0; i < inventoryList.size(); ++i) {
            final CompoundTag tag = inventoryList.get(i);
            final int slot = tag.getByte("Slot") & 0xFF;
            if (slot >= 0 && slot < this.items.length) this.items[slot] = new ItemInstance(tag);
        }
    }
    
    @Override
    public void save(final CompoundTag base) {
        super.save(base);
        final ListTag<CompoundTag> listTag = new ListTag<>();

        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null) {
                final CompoundTag tag = new CompoundTag();
                tag.putByte("Slot", (byte)i);
                this.items[i].save(tag);
                listTag.add(tag);
            }
        }
        base.put("Items", listTag);
    }
    
    public int getMaxStackSize() {
        return Container.LARGE_MAX_STACK_SIZE;
    }
    
    public boolean stillValid(final Player player) {
        if (this.level.getTileEntity(this.x, this.y, this.z) != this) return false;
        if (player.distanceToSqr(this.x + 0.5, this.y + 0.5, this.z + 0.5) > 8 * 8) return false;
        return true;
    }
}
