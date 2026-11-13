// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import java.util.Random;
import net.minecraft.world.level.block.entity.MobSpawnerTileEntity;
import net.minecraft.world.level.block.entity.TileEntity;
import net.minecraft.world.level.material.Material;

public class BlockLogicMobSpawner extends EntityBlock
{
    protected BlockLogicMobSpawner(final int id, final int tex) {
        super(id, tex, Material.stone);
    }
    
    @Override
    protected TileEntity newTileEntity() {
        return new MobSpawnerTileEntity();
    }
    
    @Override
    public int getResource(final int data, final Random random) {
        return 0;
    }
    
    @Override
    public int getResourceCount(final Random random) {
        return 0;
    }
    
    @Override
    public boolean isSolidRender() {
        return false;
    }
}
