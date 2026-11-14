// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.renderer.entity;

import net.minecraft.world.entity.animal.EntityWolf;
import net.minecraft.client.model.Model;

public class WolfRenderer extends MobRenderer<EntityWolf>
{
    public WolfRenderer(final Model model, final float shadow) {
        super(model, shadow);
    }
    
    public void render(final EntityWolf wolf, final double x, final double y, final double z, final float rot, final float a) {
        super.render(wolf, x, y, z, rot, a);
    }
    
    protected float getBob(final EntityWolf wolf, final float a) {
        return wolf.getTailAngle();
    }
    
    protected void scale(final EntityWolf wolf, final float a) {
    }
}
