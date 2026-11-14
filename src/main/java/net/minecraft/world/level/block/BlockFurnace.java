// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import net.minecraft.Facing;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.EntityItem;
import net.minecraft.world.item.ItemInstance;
import util.Mth;
import net.minecraft.world.entity.EntityMob;
import net.minecraft.world.level.block.tileentity.TileEntity;
import net.minecraft.world.level.block.tileentity.TileEntityFurnace;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.level.LevelSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import java.util.Random;

public class BlockFurnace extends EntityBlock
{
    private Random random = new Random();
    private final boolean lit;
    private static boolean noDrop = false;
    
    protected BlockFurnace(final int id, final boolean lit) {
        super(id, Material.stone);
        this.lit = lit;
        this.tex = 45;
    }
    
    @Override
    public int getResource(final int data, final Random random) {
        return Blocks.furnace.id;
    }
    
    @Override
    public void onPlace(final Level level, final int x, final int y, final int z) {
        super.onPlace(level, x, y, z);
        this.recalcLockDir(level, x, y, z);
    }
    
    private void recalcLockDir(final Level level, final int x, final int y, final int z) {
        if (level.isClientSide) {
            return;
        }

        final int n = level.getTile(x, y, z - 1); // face = 2
        final int s = level.getTile(x, y, z + 1); // face = 3
        final int w = level.getTile(x - 1, y, z); // face = 4
        final int e = level.getTile(x + 1, y, z); // face = 5

        int lockDir = 3;
        if (Block.solid[n] && !Block.solid[s]) lockDir = 3;
        if (Block.solid[s] && !Block.solid[n]) lockDir = 2;
        if (Block.solid[w] && !Block.solid[e]) lockDir = 5;
        if (Block.solid[e] && !Block.solid[w]) lockDir = 4;
        level.setData(x, y, z, lockDir);
    }
    
    @Override
    public int getTexture(final LevelSource level, final int x, final int y, final int z, final int face) {
        if (face == Facing.UP) return this.tex + 17;
        if (face == Facing.DOWN) return this.tex + 17;

        if (face != level.getData(x, y, z)) return this.tex;
        if (this.lit) return this.tex + 16;
        return this.tex - 1;
    }
    
    @Override
    public void animateTick(final Level level, final int xt, final int yt, final int zt, final Random random) {
        if (!this.lit) return;

        final int dir = level.getData(xt, yt, zt);

        final float x = xt + 0.5f;
        final float y = yt + 0.0f + random.nextFloat() * 6.0f / 16.0f;
        final float z = zt + 0.5f;
        final float r = 0.52f;
        final float ss = random.nextFloat() * 0.6f - 0.3f;

        if (dir == 4) {
            level.addParticle("smoke", x - r, y, z + ss, 0.0, 0.0, 0.0);
            level.addParticle("flame", x - r, y, z + ss, 0.0, 0.0, 0.0);
        }
        else if (dir == 5) {
            level.addParticle("smoke", x + r, y, z + ss, 0.0, 0.0, 0.0);
            level.addParticle("flame", x + r, y, z + ss, 0.0, 0.0, 0.0);
        }
        else if (dir == 2) {
            level.addParticle("smoke", x + ss, y, z - r, 0.0, 0.0, 0.0);
            level.addParticle("flame", x + ss, y, z - r, 0.0, 0.0, 0.0);
        }
        else if (dir == 3) {
            level.addParticle("smoke", x + ss, y, z + r, 0.0, 0.0, 0.0);
            level.addParticle("flame", x + ss, y, z + r, 0.0, 0.0, 0.0);
        }
    }
    
    @Override
    public int getTexture(final int face) {
        if (face == Facing.UP) return this.tex + 17;
        if (face == Facing.DOWN) return this.tex + 17;
        if (face == Facing.SOUTH) return this.tex - 1;
        return this.tex;
    }
    
    @Override
    public boolean use(final Level level, final int x, final int y, final int z, final EntityPlayer player) {
        if (level.isClientSide) {
            return true;
        }

        TileEntityFurnace furnace = (TileEntityFurnace) level.getTileEntity(x, y, z);
        player.openFurnace(furnace);
        return true;
    }
    
    public static void setLit(final boolean lit, final Level level, final int x, final int y, final int z) {
        final int data = level.getData(x, y, z);
        final TileEntity te = level.getTileEntity(x, y, z);

        BlockFurnace.noDrop = true;
        if (lit) level.setTile(x, y, z, Blocks.furnace_lit.id);
        else level.setTile(x, y, z, Blocks.furnace.id);
        BlockFurnace.noDrop = false;

        level.setData(x, y, z, data);
        te.clearRemoved();
        level.setTileEntity(x, y, z, te);
    }
    
    @Override
    protected TileEntity newTileEntity() {
        return new TileEntityFurnace();
    }
    
    @Override
    public void setPlacedBy(final Level level, final int x, final int y, final int z, final EntityMob by) {
        final int dir = Mth.floor(by.yRot * 4.0f / 360.0f + 0.5) & 0x3;

        if (dir == 0) level.setData(x, y, z, Facing.NORTH);
        if (dir == 1) level.setData(x, y, z, Facing.EAST);
        if (dir == 2) level.setData(x, y, z, Facing.SOUTH);
        if (dir == 3) level.setData(x, y, z, Facing.WEST);
    }
    
    @Override
    public void onRemove(final Level level, final int x, final int y, final int z) {
        if (!BlockFurnace.noDrop) {
            final Container container = (TileEntityFurnace)level.getTileEntity(x, y, z);
            for (int i = 0; i < container.getContainerSize(); ++i) {
                final ItemInstance item = container.getItem(i);
                if (item != null) {
                    final float xo = this.random.nextFloat() * 0.8f + 0.1f;
                    final float yo = this.random.nextFloat() * 0.8f + 0.1f;
                    final float zo = this.random.nextFloat() * 0.8f + 0.1f;

                    while (item.count > 0) {
                        int count = this.random.nextInt(21) + 10;
                        if (count > item.count) count = item.count;
                        item.count -= count;

                        ItemInstance newItem = new ItemInstance(item.id, count, item.getAuxValue());
                        final EntityItem itemEntity = new EntityItem(level, x + xo, y + yo, z + zo, newItem);
                        final float pow = 0.05f;
                        itemEntity.xd = (float)this.random.nextGaussian() * pow;
                        itemEntity.yd = (float)this.random.nextGaussian() * pow + 0.2f;
                        itemEntity.zd = (float)this.random.nextGaussian() * pow;
                        level.addEntity(itemEntity);
                    }
                }
            }
        }
        super.onRemove(level, x, y, z);
    }

}
