// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.achievement.StatsScreen;
import net.minecraft.client.gui.achievement.AchievementScreen;
import net.minecraft.client.gui.Button;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.Lighting;
import net.minecraft.stats.Achievements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;

import java.util.LinkedList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

public class InventoryScreen extends AbstractContainerScreen
{
    private float xMouse;
    private float yMouse;
    int BUTTON_PREVIOUS_PAGE = 2;
    int BUTTON_NEXT_PAGE = 3;
    int currentPage = 1;
    int pagesTotal = 1;

    private final LinkedList<ItemInstance> allItemsInGame = new LinkedList<>();
    
    public InventoryScreen(final Player player) {
        super(player.inventoryMenu);

        this.passEvents = true;
        player.awardStat(Achievements.openInventory, 1);
    }
    
    @Override
    public void init() {
        this.buttons.clear();
        pollAndPopulateGameItems();
        this.buttons.add(new Button(BUTTON_PREVIOUS_PAGE,(this.width-128),0,32,20,"Prev."));
        this.buttons.add(new Button(BUTTON_NEXT_PAGE,(this.width-32),0,32,20,"Next"));
        pagesTotal = ((int)(Math.round(allItemsInGame.size()/(((this.height-20.0)/16)*8))));
    }
    
    @Override
    protected void renderLabels() {
        this.font.draw("Crafting", 84 + 2, 8 * 2, 0x404040);
    }
    
    @Override
    public void render(final int xm, final int ym, final float a) {
        this.drawCenteredString(this.font,"Page:"+currentPage+"/"+ pagesTotal,(this.width-64),6,0xFFFFFF);
        super.render(xm, ym, a);
        this.xMouse = (float)xm;
        this.yMouse = (float)ym;
        renderFEI();

    }

    public void renderFEI(){
        int drawingRow = 0;
        int drawingColumn = 0;
        int pageOffset = (((this.height-20)/16)*8);
        boolean drawing = true;
        for (int i = (currentPage-1)*(pageOffset); i < allItemsInGame.size(); i++) {
            if (drawing) Gui.itemRenderer.renderGuiItem(this.minecraft.font, this.minecraft.textures, allItemsInGame.get(i), (this.width - 128) + (drawingColumn * 16), 20 + (drawingRow * 16));
            drawingColumn++;
            if (drawingColumn == 8) {
                drawingRow++;
                drawingColumn = 0;
            }
            if (drawingRow >= pageOffset/8){
                drawing = false;
            }


        }
    }
    
    @Override
    protected void renderBg(final float a) {
        final int tex = this.minecraft.textures.loadTexture("/gui/inventory.png");
        glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.textures.bind(tex);
        final int xo = (this.width - this.imageWidth) / 2;
        final int yo = (this.height - this.imageHeight) / 2;
        this.blit(xo, yo, 0, 0, this.imageWidth, this.imageHeight);

        glEnable(GL_RESCALE_NORMAL);
        glEnable(GL_COLOR_MATERIAL);

        glPushMatrix();
        glTranslatef((float)(xo + 51), (float)(yo + 75), 50.0f);
        final float ss = 30.0f;
        glScalef(-ss, ss, ss);
        glRotatef(180.0f, 0.0f, 0.0f, 1.0f);

        final float oybr = this.minecraft.player.yBodyRot;
        final float oyr = this.minecraft.player.yRot;
        final float oxr = this.minecraft.player.xRot;

        final float xd = xo + 51 - this.xMouse;
        final float yd = yo + 75 - 50 - this.yMouse;

        glRotatef(45 + 90, 0.0f, 1.0f, 0.0f);
        Lighting.turnOn();
        glRotatef(-45 - 90, 0.0f, 1.0f, 0.0f);

        glRotatef(-(float)Math.atan(yd / 40.0f) * 20.0f, 1.0f, 0.0f, 0.0f);

        this.minecraft.player.yBodyRot = (float)Math.atan(xd / 40.0f) * 20.0f;
        this.minecraft.player.yRot = (float)Math.atan(xd / 40.0f) * 40.0f;
        this.minecraft.player.xRot = -(float)Math.atan(yd / 40.0f) * 20.0f;
        this.minecraft.player.emission = 1.0f;
        glTranslatef(0.0f, this.minecraft.player.heightOffset, 0.0f);
        EntityRenderDispatcher.instance.playerRotY = 180.0f;
        EntityRenderDispatcher.instance.render(this.minecraft.player, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        this.minecraft.player.emission = 0.0f;
        this.minecraft.player.yBodyRot = oybr;
        this.minecraft.player.yRot = oyr;
        this.minecraft.player.xRot = oxr;
        glPopMatrix();
        Lighting.turnOff();
        glDisable(GL_RESCALE_NORMAL);
    }
    
    @Override
    protected void buttonClicked(final Button button) {
        if (button.id == 0) {
            this.minecraft.setScreen(new AchievementScreen(this.minecraft.stats));
        }
        if (button.id == 1) {
            this.minecraft.setScreen(new StatsScreen(this, this.minecraft.stats));
        }
        if (button.id == BUTTON_NEXT_PAGE){
             if (currentPage > 0 && currentPage < pagesTotal) currentPage++;
        }
        if (button.id == BUTTON_PREVIOUS_PAGE){
            if (currentPage <= pagesTotal && currentPage != 1) currentPage--;
        }
    }

    protected void pollAndPopulateGameItems(){
        allItemsInGame.clear();
        for (int i = 0; i <  Item.items.length; i++) {
            if (Item.items[i] != null){
                allItemsInGame.add(new ItemInstance(Item.items[i]));
            }

        }


    }

}
