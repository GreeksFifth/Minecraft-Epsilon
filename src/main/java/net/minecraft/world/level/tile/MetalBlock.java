// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.tile;

import net.minecraft.world.level.material.Material;

public class MetalBlock extends Block
{
    public MetalBlock(final int id, final int tex) {
        super(id, Material.metal);
        this.tex = tex;
    }
    
    @Override
    public int getTexture(final int face) {
        return this.tex;
    }
}
