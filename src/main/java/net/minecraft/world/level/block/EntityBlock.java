// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import net.minecraft.world.level.block.tileentity.TileEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
@Deprecated
/* TODO: DELETE THIS! This is the secondary, *bad* way of giving a block a TileEntity!
    -TheMidford&UselessBullets
 */
public abstract class EntityBlock extends Block
{
    protected EntityBlock(final int id, final Material material) {
        super(id, material);
        EntityBlock.isEntityTile[id] = true;
    }
    
    protected EntityBlock(final int id, final int tex, final Material material) {
        super(id, tex, material);
        EntityBlock.isEntityTile[id] = true;
    }
    
    @Override
    public void onPlace(final Level level, final int x, final int y, final int z) {
        super.onPlace(level, x, y, z);
        level.setTileEntity(x, y, z, this.newTileEntity());
    }
    
    @Override
    public void onRemove(final Level level, final int x, final int y, final int z) {
        super.onRemove(level, x, y, z);
        level.removeTileEntity(x, y, z);
    }
    
    protected abstract TileEntity newTileEntity();
}
