// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.Level;

import java.util.Random;
import net.minecraft.world.level.material.Material;

public class BlockLogicSnow extends Block
{
    protected BlockLogicSnow(final int id, final int tex) {
        super(id, tex, Material.snow);
        this.setTicking(true);
    }
    
    @Override
    public int getResource(final int data, final Random random) {
        return Items.snowBall.id;
    }
    
    @Override
    public int getResourceCount(final Random random) {
        return 4;
    }
    
    @Override
    public void tick(final Level level, final int x, final int y, final int z, final Random random) {
        if (level.getBrightness(LightLayer.Block, x, y, z) > 11) {
            this.spawnResources(level, x, y, z, level.getData(x, y, z));
            level.setTile(x, y, z, 0);
        }
    }
}
