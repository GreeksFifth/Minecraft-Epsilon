// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.gui.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.achievement.StatsScreen;
import net.minecraft.client.gui.achievement.AchievementScreen;
import net.minecraft.client.gui.Button;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.Lighting;
import net.minecraft.stats.Achievements;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.level.block.Blocks;

import java.util.LinkedList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

public class InventoryScreen extends AbstractContainerScreen
{
    private float xMouse;
    private float yMouse;
    int BUTTON_PREVIOUS_PAGE = 2;
    int BUTTON_NEXT_PAGE = 3;
    int BUTTON_INVCLEAR = 4;
    int BUTTON_CREATIVETOGGLE = 5;
    int currentPage = 1;
    int pagesTotal = 1;
    int endOfImage;
    int slotsX;
    int slotsY;
    int slotsStartX;
    int slotsStartY;
    int pageOffset;
    int slotwidth = 16;
    int slotheight = 16;
    LinkedList<Integer> slotAnchorsXPos  = new LinkedList<>();
    LinkedList<Integer> slotAnchorsYPos  = new LinkedList<>();
    Minecraft mc = Minecraft.instance;

    private final LinkedList<ItemInstance> allItemsInGame = new LinkedList<>();
    
    public InventoryScreen(final EntityPlayer player) {
        super(player.inventoryMenu);

        this.passEvents = true;
        player.awardStat(Achievements.openInventory, 1);
    }
    
    @Override
    public void init() {
        currentPage = 1;
        endOfImage = ((this.width - this.imageWidth) / 2)+this.imageWidth;
        slotsX = ((this.width-endOfImage)/16);
        slotsY = (this.height-20)/16;
        slotsStartX = endOfImage+((this.width-endOfImage)%16);
        slotsStartY = 21;
        this.buttons.clear();
        if (mc.options.enableEIV) {
            pollAndPopulateGameItems();
            this.buttons.add(new Button(BUTTON_PREVIOUS_PAGE, slotsStartX, 0, 32, 20, "Prev."));
            this.buttons.add(new Button(BUTTON_NEXT_PAGE, (this.width - 32), 0, 32, 20, "Next"));
            if (Minecraft.DEBUG_MODE) {
                this.buttons.add(new Button(BUTTON_INVCLEAR, 0, this.height-20, 96, 20, "Clear Inventory"));
                this.buttons.add(new Button(BUTTON_CREATIVETOGGLE, 0, 0, 96, 20, "Toggle Creative"));
            }
            pagesTotal = ((int)Math.ceil(allItemsInGame.size() / (float)(slotsX*slotsY)));
            slotAnchorsXPos.clear();
            slotAnchorsYPos.clear();
            for (int i = 0; i < slotsY; i++) {
                for (int j = 0; j < slotsX; j++) {
                    slotAnchorsXPos.add(slotsStartX+(j*slotwidth));
                    slotAnchorsYPos.add(slotsStartY+(i*slotheight));
                }
            }

        }

    }

    @Override
    protected void renderLabels() {
        this.font.draw("Crafting", 84 + 2, 8 * 2, 0x404040);
    }
    
    @Override
    public void render(final int xm, final int ym, final float a) {
        super.render(xm, ym, a);
        this.xMouse = (float)xm;
        this.yMouse = (float)ym;
        if (mc.options.enableEIV) {
            renderFEI();
        }
    }

    public void renderFEI(){
        pageOffset = (currentPage-1)*slotsX*slotsY;
        glPushMatrix();
        glTranslatef(0,0,1);
        Lighting.turnOff();
        this.drawCenteredString(this.font,"Page:"+currentPage+"/"+ pagesTotal,((slotsStartX)+this.width)/2,6,0xFFFFFF);
        Lighting.turnOn();
        glPopMatrix();
        for (int i = 0; i < slotsX*slotsY; i++) {
            if (i+pageOffset>=allItemsInGame.size()) break;
            Lighting.turnOff();
            Gui.itemRenderer.renderGuiItem(this.font, mc.textures, allItemsInGame.get(i+pageOffset), slotAnchorsXPos.get(i), slotAnchorsYPos.get(i));
            Lighting.turnOn();
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
        if (button.id == BUTTON_INVCLEAR){
            for (int i = 0; i < Minecraft.instance.player.inventory.getContainerSize(); i++) {
                Minecraft.instance.player.inventory.removeItem(i,256);

            }
        }
        if (button.id == BUTTON_CREATIVETOGGLE){
            mc.player.isCreative = !mc.player.isCreative;
        }
    }

    protected void pollAndPopulateGameItems(){
        allItemsInGame.clear();
        for (int i = 0; i <  Item.items.length; i++) {
            if (Item.items[i] == null) continue;
            allItemsInGame.add(new ItemInstance(Item.items[i]));
                if (Item.items[i].id == Blocks.wool.id){
                    for (int j = 1; j < 16; j++) {
                        allItemsInGame.add(new ItemInstance(Item.items[i],1,j));
                }
            }
        }
    }

    @Override
    protected void mouseClicked(final int x, final int y, final int buttonNum) {
        for (int i = 0; i < slotsY; i++) {
            for (int j = 0; j < slotsX; j++) {
                int xStart = slotsStartX + (j * slotwidth);
                if (x > xStart && x < xStart + slotwidth) {
                    int yStart = slotsStartY + (i * slotheight);
                    if (y > yStart && y < yStart + slotheight) {
                        int itemSlot = (i * slotsX + j) + pageOffset;
                        if (itemSlot >= allItemsInGame.size()) return;
                            if (mc.player.inventory.getCarried() == null) {
                                if (buttonNum == 0) {
                                    mc.player.inventory.add(new ItemInstance(allItemsInGame.get(itemSlot).id, allItemsInGame.get(itemSlot).getMaxStackSize(), allItemsInGame.get(itemSlot).getAuxValue()));
                                } else {
                                    mc.player.inventory.add(new ItemInstance(allItemsInGame.get(itemSlot).id, 1, allItemsInGame.get(itemSlot).getAuxValue()));
                                }
                            } else {
                                mc.player.inventory.setCarried(null);
                            }

                    }
                }
            }
        }
        super.mouseClicked(x, y, buttonNum);
    }
}
