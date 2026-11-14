// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import java.util.Random;
import net.minecraft.world.level.material.Material;

public class BlockStone extends Block
{
    public BlockStone(final int id, final int tex) {
        super(id, tex, Material.stone);
    }
    
    @Override
    public int getResource(final int data, final Random random) {
        return Blocks.stoneBrick.id;
    }
}
