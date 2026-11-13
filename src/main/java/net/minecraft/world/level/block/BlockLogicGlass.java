// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import java.util.Random;
import net.minecraft.world.level.material.Material;

public class BlockLogicGlass extends BlockLogicTranslucent
{
    public BlockLogicGlass(final int id, final int tex, final Material material, final boolean allowSame) {
        super(id, tex, material, allowSame);
    }
    
    @Override
    public int getResourceCount(final Random random) {
        return 0;
    }
    
    @Override
    public int getRenderLayer() {
        return 0;
    }
}
