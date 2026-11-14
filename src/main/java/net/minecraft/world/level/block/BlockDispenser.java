// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import net.minecraft.Facing;
import net.minecraft.world.Container;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelEvent;
import util.Mth;
import net.minecraft.world.entity.EntityMob;
import net.minecraft.world.level.block.tileentity.TileEntity;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.entity.item.EntityItem;
import net.minecraft.world.entity.projectile.EntityProjectileSnowball;
import net.minecraft.world.entity.projectile.EntityProjectileEgg;
import net.minecraft.world.entity.projectile.EntityProjectileArrow;
import net.minecraft.world.level.block.tileentity.TileEntityDispenser;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.level.LevelSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import java.util.Random;

public class BlockDispenser extends EntityBlock
{
    private Random random = new Random();
    
    protected BlockDispenser(final int id) {
        super(id, Material.stone);
        this.tex = 45;
    }
    
    @Override
    public int getTickDelay() {
        return 0;
    }
    
    @Override
    public int getResource(final int data, final Random random) {
        return Blocks.dispenser.id;
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
        if (face == Facing.UP || face == Facing.DOWN) return this.tex + 17;
        int dir = level.getData(x, y, z);
        if (face != dir) return this.tex;
        return this.tex + 1;
    }
    
    @Override
    public int getTexture(final int face) {
        if (face == Facing.UP || face == Facing.DOWN) return this.tex + 17;
        if (face == Facing.SOUTH) return this.tex + 1;
        return this.tex;
    }
    
    @Override
    public boolean use(final Level level, final int x, final int y, final int z, final EntityPlayer player) {
        if (level.isClientSide) {
            return true;
        }

        TileEntityDispenser trap = (TileEntityDispenser) level.getTileEntity(x, y, z);
        player.openTrap(trap);

        return true;
    }
    
    private void fireArrow(final Level level, final int x, final int y, final int z, final Random random) {
        final int lockDir = level.getData(x, y, z);
        final float power = 1.1f;
        final int accuracy = 6;

        int xd = 0, zd = 0;
        if (lockDir == Facing.SOUTH) zd = 1;
        else if (lockDir == Facing.NORTH) zd = -1;
        else if (lockDir == Facing.EAST) xd = 1;
        else xd = -1;

        TileEntityDispenser trap = (TileEntityDispenser) level.getTileEntity(x, y, z);
        final ItemInstance item = trap.removeSequentialItem(); // Argent - Was formerly removeRandomItem()

        final double xp = x + xd * 0.6 + 0.5;
        final double yp = y + 0.5;
        final double zp = z + zd * 0.6 + 0.5;
        if (item == null) {
            level.levelEvent(LevelEvent.SOUND_CLICK_FAIL, x, y, z, 0);
        }
        else {
            if (item.id == Items.arrow.id) {
                final EntityProjectileArrow arrow = new EntityProjectileArrow(level, xp, yp, zp);
                arrow.shoot(xd, 0.1f, zd, power, accuracy);
                arrow.pickup = true;
                level.addEntity(arrow);
                level.levelEvent(LevelEvent.SOUND_LAUNCH, x, y, z, 0);
            }
            else if (item.id == Items.egg.id) {
                final EntityProjectileEgg egg = new EntityProjectileEgg(level, xp, yp, zp);
                egg.shoot(xd, 0.1f, zd, power, accuracy);
                level.addEntity(egg);
                level.levelEvent(LevelEvent.SOUND_LAUNCH, x, y, z, 0);
            }
            else if (item.id == Items.snowBall.id) {
                final EntityProjectileSnowball snowball = new EntityProjectileSnowball(level, xp, yp, zp);
                snowball.shoot(xd, 0.1f, zd, power, accuracy);
                level.addEntity(snowball);
                level.levelEvent(LevelEvent.SOUND_LAUNCH, x, y, z, 0);
            }
            else {
                final EntityItem itemEntity = new EntityItem(level, xp, yp - 0.3, zp, item);

                final double pow = random.nextDouble() * 0.1 + 0.2;
                itemEntity.xd = xd * pow;
                itemEntity.yd = 0.2f;
                itemEntity.zd = zd * pow;

                itemEntity.xd += random.nextGaussian() * 0.0075f * accuracy;
                itemEntity.yd += random.nextGaussian() * 0.0075f * accuracy;
                itemEntity.zd += random.nextGaussian() * 0.0075f * accuracy;

                level.addEntity(itemEntity);
                level.levelEvent(LevelEvent.SOUND_CLICK, x, y, z, 0);
            }
            level.levelEvent(LevelEvent.PARTICLES_SHOOT, x, y, z, xd + 1 + (zd + 1) * 3);
        }
    }
    
    @Override
    public void neighborChanged(final Level level, final int x, final int y, final int z, final int type) {
        if (type > 0 && Block.BLOCKS[type].isSignalSource()) {
            boolean signal = level.hasNeighborSignal(x, y, z) || level.hasNeighborSignal(x, y + 1, z);
            if (signal) {
                level.addToTickNextTick(x, y, z, this.id, this.getTickDelay());
            }
        }
    }
    
    @Override
    public void tick(final Level level, final int x, final int y, final int z, final Random random) {
        if (level.hasNeighborSignal(x, y, z) || level.hasNeighborSignal(x, y + 1, z)) {
            this.fireArrow(level, x, y, z, random);
        }
    }
    
    @Override
    protected TileEntity newTileEntity() {
        return new TileEntityDispenser();
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
        final Container container = (TileEntityDispenser)level.getTileEntity(x, y, z);
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
        super.onRemove(level, x, y, z);
    }
}
