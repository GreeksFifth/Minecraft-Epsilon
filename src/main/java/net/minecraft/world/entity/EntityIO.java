// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.entity;

import net.minecraft.world.entity.item.EntityBoat;
import net.minecraft.world.entity.item.EntityMinecart;
import net.minecraft.world.entity.item.EntityFallingBlock;
import net.minecraft.world.entity.item.EntityTNTPrimed;
import net.minecraft.world.entity.animal.EntityWolf;
import net.minecraft.world.entity.animal.EntitySquid;
import net.minecraft.world.entity.animal.EntityChicken;
import net.minecraft.world.entity.animal.EntityCow;
import net.minecraft.world.entity.animal.EntitySheep;
import net.minecraft.world.entity.animal.EntityPig;
import net.minecraft.world.entity.monster.EntityPigZombie;
import net.minecraft.world.entity.monster.EntityGhast;
import net.minecraft.world.entity.monster.EntitySlime;
import net.minecraft.world.entity.monster.EntityZombie;
import net.minecraft.world.entity.monster.EntityGiant;
import net.minecraft.world.entity.monster.EntitySpider;
import net.minecraft.world.entity.monster.EntitySkeletonArcher;
import net.minecraft.world.entity.monster.EntityCreeper;
import net.minecraft.world.entity.monster.EntityMobHostile;
import net.minecraft.world.entity.item.EntityItem;
import net.minecraft.world.entity.projectile.EntityProjectileSnowball;
import net.minecraft.world.entity.projectile.EntityProjectileArrow;
import java.util.HashMap;
import com.mojang.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import java.util.Map;

public class EntityIO
{
    private static Map<String, Class<? extends Entity>> idClassMap = new HashMap<>();
    private static Map<Class<? extends Entity>, String> classIdMap = new HashMap<>();
    private static Map<Integer, Class<? extends Entity>> numClassMap = new HashMap<>();
    private static Map<Class<? extends Entity>, Integer> classNumMap = new HashMap<>();

    private static void setId(final Class<? extends Entity> clazz, final String id, final int idNum) {
        EntityIO.idClassMap.put(id, clazz);
        EntityIO.classIdMap.put(clazz, id);
        EntityIO.numClassMap.put(idNum, clazz);
        EntityIO.classNumMap.put(clazz, idNum);
    }

    static {
        setId(EntityProjectileArrow.class, "Arrow", 10);
        setId(EntityProjectileSnowball.class, "Snowball", 11);
        setId(EntityItem.class, "Item", 1);
        setId(EntityPainting.class, "Painting", 9);
        setId(EntityMob.class, "Mob", 48);
        setId(EntityMobHostile.class, "Monster", 49);
        setId(EntityCreeper.class, "Creeper", 50);
        setId(EntitySkeletonArcher.class, "Skeleton", 51);
        setId(EntitySpider.class, "Spider", 52);
        setId(EntityGiant.class, "Giant", 53);
        setId(EntityZombie.class, "Zombie", 54);
        setId(EntitySlime.class, "Slime", 55);
        setId(EntityGhast.class, "Ghast", 56);
        setId(EntityPigZombie.class, "PigZombie", 57);
        setId(EntityPig.class, "Pig", 90);
        setId(EntitySheep.class, "Sheep", 91);
        setId(EntityCow.class, "Cow", 92);
        setId(EntityChicken.class, "Chicken", 93);
        setId(EntitySquid.class, "Squid", 94);
        setId(EntityWolf.class, "Wolf", 95);
        setId(EntityTNTPrimed.class, "PrimedTnt", 20);
        setId(EntityFallingBlock.class, "FallingSand", 21);
        setId(EntityMinecart.class, "Minecart", 40);
        setId(EntityBoat.class, "Boat", 41);
    }

    public static Entity newEntity(final String id, final Level level) {
        Entity entity = null;
        try {
            final Class<? extends Entity> clazz = EntityIO.idClassMap.get(id);
            if (clazz != null) entity = clazz.getConstructor(Level.class).newInstance(level);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public static Entity loadStatic(final CompoundTag tag, final Level level) {
        Entity entity = null;
        try {
            final Class<? extends Entity> clazz = EntityIO.idClassMap.get(tag.getString("id"));
            if (clazz != null) entity = clazz.getConstructor(Level.class).newInstance(level);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        if (entity != null) entity.load(tag);
        else System.out.println("Skipping Entity with id " + tag.getString("id"));
        return entity;
    }

    public static Entity newById(final int id, final Level level) {
        Entity entity = null;
        try {
            final Class<? extends Entity> clazz = EntityIO.numClassMap.get(id);
            if (clazz != null) entity = clazz.getConstructor(Level.class).newInstance(level);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        if (entity == null) System.out.println("Skipping Entity with id " + id);
        return entity;
    }

    public static int getId(final Entity entity) {
        return EntityIO.classNumMap.get(entity.getClass());
    }

    public static String getEncodeId(final Entity entity) {
        return EntityIO.classIdMap.get(entity.getClass());
    }
}
