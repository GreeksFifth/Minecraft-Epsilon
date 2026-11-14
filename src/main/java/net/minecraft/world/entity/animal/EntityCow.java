// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.entity.animal;

import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.entity.player.EntityPlayer;
import com.mojang.nbt.CompoundTag;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class EntityCow extends EntityMobPassive
{
    public EntityCow(final Level level) {
        super(level);
        this.textureName = "/mob/cow.png";
        this.setSize(0.9f, 1.3f);
    }
    
    @Override
    public void addAdditionalSaveData(final CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
    }
    
    @Override
    public void readAdditionalSaveData(final CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
    }
    
    @Override
    protected String getAmbientSound() {
        return "mob.cow";
    }
    
    @Override
    protected String getHurtSound() {
        return "mob.cowhurt";
    }
    
    @Override
    protected String getDeathSound() {
        return "mob.cowhurt";
    }
    
    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }
    
    @Override
    protected int getDeathLoot() {
        return Items.leather.id;
    }
    
    @Override
    public boolean interact(final EntityPlayer player) {
        final ItemInstance item = player.inventory.getSelected();
        if (item != null && item.id == Items.bucket_empty.id) {
            player.inventory.setItem(player.inventory.selected, new ItemInstance(Items.milk));
            return true;
        }
        return false;
    }
}
