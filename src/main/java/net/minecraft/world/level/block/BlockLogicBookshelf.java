// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import java.util.Random;

import net.minecraft.Facing;
import net.minecraft.world.level.material.Material;

public class BlockLogicBookshelf extends Block
{
    public BlockLogicBookshelf(final int id, final int tex) {
        super(id, tex, Material.wood);
    }
    
    @Override
    public int getTexture(final int face) {
        if (face <= Facing.UP) return 4;
        return this.tex;
    }
    
    @Override
    public int getResourceCount(final Random random) {
        return 0;
    }
}
