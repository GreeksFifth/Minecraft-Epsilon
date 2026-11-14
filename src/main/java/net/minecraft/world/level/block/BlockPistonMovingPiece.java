// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import net.minecraft.Facing;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelSource;
import java.util.Random;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.world.level.block.tileentity.TileEntityPistonPiece;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.tileentity.TileEntity;
import net.minecraft.world.level.material.Material;

public class BlockPistonMovingPiece extends EntityBlock
{
    public BlockPistonMovingPiece(final int id) {
        super(id, Material.piston);
        this.setDestroyTime(Block.INDESTRUCTIBLE_DESTROY_TIME);
    }
    
    @Override
    protected TileEntity newTileEntity() {
        return null;
    }
    
    @Override
    public void onPlace(final Level level, final int x, final int y, final int z) {
    }
    
    @Override
    public void onRemove(final Level level, final int x, final int y, final int z) {
        final TileEntity tileEntity = level.getTileEntity(x, y, z);
        if (tileEntity != null && tileEntity instanceof TileEntityPistonPiece) {
            ((TileEntityPistonPiece)tileEntity).finalTick();
        }
        else {
            super.onRemove(level, x, y, z);
        }
    }
    
    @Override
    public boolean mayPlace(final Level level, final int x, final int y, final int z) {
        return false;
    }
    
    @Override
    public boolean mayPlace(final Level level, final int x, final int y, final int z, final int face) {
        return false;
    }
    
    @Override
    public int getRenderShape() {
        return Block.SHAPE_INVISIBLE;
    }
    
    @Override
    public boolean isSolidRender() {
        return false;
    }
    
    @Override
    public boolean isCubeShaped() {
        return false;
    }
    
    @Override
    public boolean use(final Level level, final int x, final int y, final int z, final EntityPlayer player) {
        // this is a special case in order to help removing invisible, unbreakable, blocks in the world
        if (!level.isClientSide && level.getTileEntity(x, y, z) == null) {
            // this block is no longer valid
            level.setTile(x, y, z, 0);
            return true;
        }
        return false;
    }
    
    @Override
    public int getResource(final int data, final Random random) {
        return 0;
    }
    
    @Override
    public void spawnResources(final Level level, final int x, final int y, final int z, final int data, final float odds) {
        if (level.isClientSide) return;

        final TileEntityPistonPiece entity = this.getEntity(level, x, y, z);
        if (entity == null) {
            return;
        }

        Block.BLOCKS[entity.getId()].spawnResources(level, x, y, z, entity.getData());
    }
    
    @Override
    public void neighborChanged(final Level level, final int x, final int y, final int z, final int type) {
        if (!level.isClientSide && level.getTileEntity(x, y, z) != null) {
        }
    }
    
    public static TileEntity newMovingPieceEntity(final int block, final int data, final int facing, final boolean extending, final boolean isSourcePiston) {
        return new TileEntityPistonPiece(block, data, facing, extending, isSourcePiston);
    }
    
    @Override
    public AABB getAABB(final Level level, final int x, final int y, final int z) {
        final TileEntityPistonPiece entity = this.getEntity(level, x, y, z);
        if (entity == null) {
            return null;
        }

        // move the aabb depending on the animation
        float progress = entity.getProgress(0.0f);
        if (entity.isExtending()) {
            progress = 1.0f - progress;
        }
        return this.getAABB(level, x, y, z, entity.getId(), progress, entity.getFacing());
    }
    
    @Override
    public void updateShape(final LevelSource level, final int x, final int y, final int z) {
        final TileEntityPistonPiece entity = this.getEntity(level, x, y, z);
        if (entity != null) {
            final Block block = Block.BLOCKS[entity.getId()];
            if (block == null || block == this) {
                return;
            }
            block.updateShape(level, x, y, z);

            float progress = entity.getProgress(0.0f);
            if (entity.isExtending()) {
                progress = 1.0f - progress;
            }
            final int facing = entity.getFacing();
            this.xx0 = block.xx0 - Facing.STEP_X[facing] * progress;
            this.yy0 = block.yy0 - Facing.STEP_Y[facing] * progress;
            this.zz0 = block.zz0 - Facing.STEP_Z[facing] * progress;
            this.xx1 = block.xx1 - Facing.STEP_X[facing] * progress;
            this.yy1 = block.yy1 - Facing.STEP_Y[facing] * progress;
            this.zz1 = block.zz1 - Facing.STEP_Z[facing] * progress;
        }
    }
    
    public AABB getAABB(final Level level, final int x, final int y, final int z, final int tile, final float progress, final int facing) {
        if (tile == 0 || tile == this.id) {
            return null;
        }
        final AABB aabb = Block.BLOCKS[tile].getAABB(level, x, y, z);

        if (aabb == null) {
            return null;
        }

        // move the aabb depending on the animation
        aabb.x0 -= Facing.STEP_X[facing] * progress;
        aabb.x1 -= Facing.STEP_X[facing] * progress;
        aabb.y0 -= Facing.STEP_Y[facing] * progress;
        aabb.y1 -= Facing.STEP_Y[facing] * progress;
        aabb.z0 -= Facing.STEP_Z[facing] * progress;
        aabb.z1 -= Facing.STEP_Z[facing] * progress;
        return aabb;
    }
    
    private TileEntityPistonPiece getEntity(final LevelSource level, final int x, final int y, final int z) {
        final TileEntity tileEntity = level.getTileEntity(x, y, z);
        if (tileEntity != null && tileEntity instanceof TileEntityPistonPiece) {
            return (TileEntityPistonPiece)tileEntity;
        }
        return null;
    }
}
