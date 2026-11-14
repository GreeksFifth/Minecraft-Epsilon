// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.renderer.entity;

import net.minecraft.world.entity.animal.EntityCow;
import net.minecraft.client.model.Model;

public class CowRenderer extends MobRenderer<EntityCow>
{
    public CowRenderer(final Model model, final float shadow) {
        super(model, shadow);
    }
    
    public void render(final EntityCow cow, final double x, final double y, final double z, final float rot, final float a) {
        super.render(cow, x, y, z, rot, a);
    }
}
