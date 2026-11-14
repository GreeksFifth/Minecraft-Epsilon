// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.biome;

import net.minecraft.world.level.levelgen.feature.SpruceFeature;
import net.minecraft.world.level.levelgen.feature.PineFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import java.util.Random;

import net.minecraft.world.entity.animal.EntityWolf;

public class TaigaBiome extends Biome
{
    public TaigaBiome() {
        this.friendlies.add(new MobSpawnerData(EntityWolf.class, 2));
    }
    
    @Override
    public Feature getTreeFeature(final Random random) {
        if (random.nextInt(3) == 0) {
            return new PineFeature();
        }
        return new SpruceFeature();
    }
}
