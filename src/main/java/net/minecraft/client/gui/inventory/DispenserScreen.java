// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.gui.inventory;

import net.minecraft.midforddevutils.MidfordBlitter;
import net.minecraft.world.inventory.TrapMenu;
import net.minecraft.world.level.block.tileentity.TileEntityDispenser;
import net.minecraft.world.entity.player.Inventory;

import static org.lwjgl.opengl.GL11.*;

public class DispenserScreen extends AbstractContainerScreen
{
    private final TileEntityDispenser te;
    int selectorX;
    int selectorY;
    int debugSelectorX = 59;
    int debugSelectorY = 14;

    public DispenserScreen(final Inventory inventory, final TileEntityDispenser trap) {
        super(new TrapMenu(inventory, trap));
        this.te = trap;
    }
    
    @Override
    protected void renderLabels() {
        this.font.draw("Dispenser", 16 + 4 + 40, 2 + 2 + 2, 0x404040);
        this.font.draw("Inventory", 8, this.imageHeight - 96 + 2, 0x404040);
        switch (te.targetSlot) {
            case 0 -> {
                selectorX = 0+debugSelectorX;
                selectorY = 0+debugSelectorY;
            }
            case 1 -> {
                selectorX = 18+debugSelectorX;
                selectorY = 0+debugSelectorY;
            }
            case 2 -> {
                selectorX = 36+debugSelectorX;
                selectorY = 0+debugSelectorY;
            }
            case 3 -> {
                selectorX = 0+debugSelectorX;
                selectorY = 18+debugSelectorY;
            }
            case 4 -> {
                selectorX = 18+debugSelectorX;
                selectorY = 18+debugSelectorY;
            }
            case 5 -> {
                selectorX = 36+debugSelectorX;
                selectorY = 18+debugSelectorY;
            }
            case 6 -> {
                selectorX = 0+debugSelectorX;
                selectorY = 36+debugSelectorY;
            }
            case 7 -> {
                selectorX = 18+debugSelectorX;
                selectorY = 36+debugSelectorY;
            }
            case 8 -> {
                selectorX = 36+debugSelectorX;
                selectorY = 36+debugSelectorY;
            }
        }
        if (te.targetSlot >= 0 && te.targetSlot <= 8){
            glPushMatrix();
            final int tex = this.minecraft.textures.loadTexture("/gui/dispenser.png");
            glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            this.minecraft.textures.bind(tex);
            MidfordBlitter.blit(selectorX, selectorY,0,0,166,22,22);
            glPopMatrix();
        }
    }
    
    @Override
    protected void renderBg(final float a) {
        final int tex = this.minecraft.textures.loadTexture("/gui/dispenser.png");
        glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.textures.bind(tex);
        final int xo = (this.width - this.imageWidth) / 2;
        final int yo = (this.height - this.imageHeight) / 2;
        this.blit(xo, yo, 0, 0, this.imageWidth, this.imageHeight);
    }
}
