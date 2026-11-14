// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.renderer.entity;

import net.minecraft.world.entity.monster.EntitySpider;
import net.minecraft.client.model.ModelSpider;

import static org.lwjgl.opengl.GL11.*;

public class SpiderRenderer extends MobRenderer<EntitySpider>
{
    public SpiderRenderer() {
        super(new ModelSpider(), 1.0f);
        this.setArmor(new ModelSpider());
    }
    
    protected float getFlipDegrees(final EntitySpider spider) {
        return 180.0f;
    }
    
    protected boolean prepareArmor(final EntitySpider spider, final int layer, final float a) {
        if (layer != 0) return false;
        this.bindTexture("/mob/spider_eyes.png");

        final float br = (1.0f - spider.getBrightness(1.0f)) * 0.5f;
        glEnable(GL_BLEND);
        glDisable(GL_ALPHA_TEST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glColor4f(1.0f, 1.0f, 1.0f, br);
        return true;
    }
}
