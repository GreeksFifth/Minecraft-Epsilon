// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.biome;

import net.minecraft.world.entity.monster.EntityPigZombie;
import net.minecraft.world.entity.monster.EntityGhast;

public class HellBiome extends Biome
{
    public HellBiome() {
        this.enemies.clear();
        this.friendlies.clear();
        this.waterFriendlies.clear();

        this.enemies.add(new MobSpawnerData(EntityGhast.class, 10));
        this.enemies.add(new MobSpawnerData(EntityPigZombie.class, 10));
    }
}
