// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import net.minecraft.Facing;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.entity.item.EntityTNTPrimed;
import java.util.Random;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;

public class BlockTNT extends Block
{
    public static final int EXPLODE_BIT = 1;
    public BlockTNT(final int id, final int tex) {
        super(id, tex, Material.explosive);
    }
    
    @Override
    public int getTexture(final int face) {
        if (face == Facing.DOWN) return this.tex + 2;
        if (face == Facing.UP) return this.tex + 1;
        return this.tex;
    }
    
    @Override
    public void onPlace(final Level level, final int x, final int y, final int z) {
        super.onPlace(level, x, y, z);
        if (level.hasNeighborSignal(x, y, z)) {
            this.destroy(level, x, y, z, EXPLODE_BIT);
            level.setTile(x, y, z, 0);
        }
    }
    
    @Override
    public void neighborChanged(final Level level, final int x, final int y, final int z, final int type) {
        if (type > 0 && Block.BLOCKS[type].isSignalSource()) {
            if (level.hasNeighborSignal(x, y, z)) {
                this.destroy(level, x, y, z, EXPLODE_BIT);
                level.setTile(x, y, z, 0);
            }
        }
    }
    
    @Override
    public int getResourceCount(final Random random) {
        return 0;
    }
    
    @Override
    public void wasExploded(final Level level, final int x, final int y, final int z) {
        final EntityTNTPrimed primed = new EntityTNTPrimed(level, x + 0.5f, y + 0.5f, z + 0.5f);
        primed.life = level.random.nextInt(primed.life / 4) + primed.life / 8;
        level.addEntity(primed);
    }
    
    @Override
    public void destroy(final Level level, final int x, final int y, final int z, final int data) {
        if (level.isClientSide) return;

        if ((data & EXPLODE_BIT) == 0x0) {
            this.popResource(level, x, y, z, new ItemInstance(Blocks.tnt.id, 1, 0));
        }
        else {
            final EntityTNTPrimed tnt = new EntityTNTPrimed(level, x + 0.5f, y + 0.5f, z + 0.5f);
            level.addEntity(tnt);
            level.playSound(tnt, "random.fuse", 1.0f, 1.0f);
        }
    }
    
    @Override
    public void attack(final Level level, final int x, final int y, final int z, final EntityPlayer player) {
        if (player.getSelectedItem() != null && player.getSelectedItem().id == Items.flintAndSteel.id) {
            level.setDataNoUpdate(x, y, z, EXPLODE_BIT);
        }
        super.attack(level, x, y, z, player);
    }
    
    @Override
    public boolean use(final Level level, final int x, final int y, final int z, final EntityPlayer player) {
        return super.use(level, x, y, z, player);
    }
}
