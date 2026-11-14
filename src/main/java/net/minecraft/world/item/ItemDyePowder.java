// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.item;

import net.minecraft.world.level.block.*;
import net.minecraft.world.entity.animal.EntitySheep;
import net.minecraft.world.entity.EntityMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.EntityPlayer;

public class ItemDyePowder extends Item
{
    public static final String[] COLOR_DESCS = new String[] {
            "black",
            "red",
            "green",
            "brown",
            "blue",
            "purple",
            "cyan",
            "silver",
            "gray",
            "pink",
            "lime",
            "yellow",
            "lightBlue",
            "magenta",
            "orange",
            "white"
    };
    public static final int[] COLOR_RGB = new int[] {
            0x1e1b1b,
            0xb3312c,
            0x3b511a,
            0x51301a,
            0x253192,
            0x7b2fbe,
            0x287697,
            0x287697,
            0x434343,
            0xd88198,
            0x41cd34,
            0xdecf2a,
            0x6689d3,
            0xc354cd,
            0xeb8844,
            0xf0f0f0
    };

    public static final int BLACK = 0;
    public static final int RED = 1;
    public static final int GREEN = 2;
    public static final int BROWN = 3;
    public static final int BLUE = 4;
    public static final int PURPLE = 5;
    public static final int CYAN = 6;
    public static final int SILVER = 7;
    public static final int GRAY = 8;
    public static final int PINK = 9;
    public static final int LIME = 10;
    public static final int YELLOW = 11;
    public static final int LIGHT_BLUE = 12;
    public static final int MAGENTA = 13;
    public static final int ORANGE = 14;
    public static final int WHITE = 15;
    
    public ItemDyePowder(final int id) {
        super(id);
        this.setStackedByData(true);
        this.setMaxDamage(0);
    }
    
    @Override
    public int getIcon(final int auxValue) {
        return this.icon + auxValue % 8 * 16 + auxValue / 8;
    }
    
    @Override
    public String getDescriptionId(final ItemInstance itemInstance) {
        return super.getDescriptionId() + "." + ItemDyePowder.COLOR_DESCS[itemInstance.getAuxValue()];
    }
    
    @Override
    public boolean useOn(final ItemInstance itemInstance, final EntityPlayer player, final Level level, final int x, final int y, final int z, final int face) {
        if (itemInstance.getAuxValue() == WHITE) {
            // bone meal is a fertilizer, so instantly grow trees and stuff
            final int tile = level.getTile(x, y, z);
            if (tile == Blocks.sapling.id) {
                if (!level.isClientSide) {
                    ((BlockSapling) Blocks.sapling).growTree(level, x, y, z, level.random);
                    itemInstance.count--;
                }
                return true;
            }
            if (tile == Blocks.crops.id) {
                if (!level.isClientSide) {
                    ((BlockWheatCrop) Blocks.crops).growCropsToMax(level, x, y, z);
                    itemInstance.count--;
                }
                return true;
            }
            if (tile == Blocks.grass.id) {
                if (!level.isClientSide) {
                    itemInstance.count--;
                    mainLoop:
                    for (int j = 0; j < 128; j++) {
                        int xx = x;
                        int yy = y + 1;
                        int zz = z;
                        for (int i = 0; i < j / 16; i++) {
                            xx += ItemDyePowder.random.nextInt(3) - 1;
                            yy += (ItemDyePowder.random.nextInt(3) - 1) * ItemDyePowder.random.nextInt(3) / 2;
                            zz += ItemDyePowder.random.nextInt(3) - 1;
                            if (level.getTile(xx, yy - 1, zz) != Blocks.grass.id || level.isSolidBlockingTile(xx, yy, zz)) {
                                continue mainLoop;
                            }
                        }

                        if (level.getTile(xx, yy, zz) == 0) {
                            if (ItemDyePowder.random.nextInt(10) != 0) {
                                level.setTileAndData(xx, yy, zz, Blocks.tallgrass.id, 1);
                            }
                            else if (ItemDyePowder.random.nextInt(3) != 0) {
                                level.setTile(xx, yy, zz, Blocks.flower.id);
                            } else {
                                level.setTile(xx, yy, zz, Blocks.rose.id);
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void interactEnemy(final ItemInstance itemInstance, final EntityMob mob) {
        if (mob instanceof EntitySheep) {
            final EntitySheep sheep = (EntitySheep)mob;
            // convert to tile-based color value (0 is white instead of black)
            final int newColor = BlockWool.getTileDataForItemAuxValue(itemInstance.getAuxValue());
            if (!sheep.isSheared() && sheep.getColor() != newColor) {
                sheep.setColor(newColor);
                itemInstance.count--;
            }
        }
    }


}
