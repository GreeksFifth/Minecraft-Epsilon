// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.biome;

import net.minecraft.world.entity.animal.EntityChicken;

public class SkyBiome extends Biome
{
    public SkyBiome() {
        this.enemies.clear();
        this.friendlies.clear();
        this.waterFriendlies.clear();

        this.friendlies.add(new MobSpawnerData(EntityChicken.class, 10));
    }
    
    @Override
    public int getSkyColor(final float temp) {
        return 0xc0c0ff;
    }
}
