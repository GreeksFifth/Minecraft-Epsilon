// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block.tileentity;

import java.util.HashMap;

import net.minecraft.network.packet.Packet;
import net.minecraft.world.level.block.BlockJukebox;
import net.minecraft.world.level.block.Block;
import com.mojang.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import java.util.Map;

public class TileEntity
{
    private static Map<String, Class<? extends TileEntity>> idClassMap = new HashMap<>();
    private static Map<Class<? extends TileEntity>, String> classIdMap = new HashMap<>();
    static {
        setId(TileEntityFurnace.class, "Furnace");
        setId(TileEntityChest.class, "Chest");
        setId(BlockJukebox.Entity.class, "RecordPlayer");
        setId(TileEntityDispenser.class, "Trap");
        setId(TileEntitySign.class, "Sign");
        setId(TileEntityMobSpawner.class, "MobSpawner");
        setId(TileEntityNoteblock.class, "Music");
        setId(TileEntityPistonPiece.class, "Piston");
    }
    private static void setId(final Class<? extends TileEntity> clazz, final String id) {
        if (TileEntity.classIdMap.containsKey(id)) throw new IllegalArgumentException("Duplicate id: " + id);
        TileEntity.idClassMap.put(id, clazz);
        TileEntity.classIdMap.put(clazz, id);
    }

    public Level level;
    public int x, y, z;

    protected boolean remove;

    public void load(final CompoundTag compoundTag) {
        this.x = compoundTag.getInt("x");
        this.y = compoundTag.getInt("y");
        this.z = compoundTag.getInt("z");
    }

    public void save(final CompoundTag compoundTag) {
        final String id = TileEntity.classIdMap.get(this.getClass());
        if (id == null) throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        compoundTag.putString("id", id);
        compoundTag.putInt("x", this.x);
        compoundTag.putInt("y", this.y);
        compoundTag.putInt("z", this.z);
    }

    public void tick() {
    }

    public static TileEntity loadStatic(final CompoundTag tag) {
        TileEntity entity = null;
        try {
            final Class<? extends TileEntity> clazz = TileEntity.idClassMap.get(tag.getString("id"));
            if (clazz != null) entity = clazz.newInstance();
        }
        catch (final Exception e) {
            e.printStackTrace();
        }

        if (entity != null) {
            entity.load(tag);
        } else {
            System.out.println("Skipping TileEntity with id " + tag.getString("id"));
        }
        return entity;
    }

    public int getData() {
        return this.level.getData(this.x, this.y, this.z);
    }

    // Useless - In b1.2 and LCE leaks
    public void setData(int data) {
        this.level.setData(this.x, this.y, this.z, data);
    }

    public void setChanged() {
        if (this.level != null) {
            this.level.tileEntityChanged(this.x, this.y, this.z, this);
        }
    }

    public double distanceToSqr(final double x, final double y, final double z) {
        final double xd = this.x + 0.5 - x;
        final double yd = this.y + 0.5 - y;
        final double zd = this.z + 0.5 - z;
        return xd * xd + yd * yd + zd * zd;
    }

    public Block getTile() {
        return Block.BLOCKS[this.level.getTile(this.x, this.y, this.z)];
    }

    public Packet getUpdatePacket() {
        return null;
    }

    public boolean isRemoved() {
        return this.remove;
    }

    public void setRemoved() {
        this.remove = true;
    }

    public void clearRemoved() {
        this.remove = false;
    }
}
