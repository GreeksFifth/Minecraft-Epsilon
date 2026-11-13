// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.levelgen.feature;

import net.minecraft.world.level.tile.BlockLogicLeaves;
import net.minecraft.world.level.tile.Block;
import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.TreeBlock;

public class PineFeature extends Feature
{
    @Override
    public boolean place(final Level level, final Random random, final int x, final int y, final int z) {
        // pines can be quite tall
        final int treeHeight = random.nextInt(5) + 7;
        final int trunkHeight = treeHeight - random.nextInt(2) - 3;
        final int topHeight = treeHeight - trunkHeight;
        final int topRadius = 1 + random.nextInt(topHeight + 1);

        boolean free = true;
        // may not be outside of y boundaries
        if (y < 1 || y + treeHeight + 1 > Level.MAX_HEIGHT) {
            return false;
        }

        // make sure there is enough space
        for (int yy = y; yy <= y + 1 + treeHeight && free; ++yy) {
            int r;
            if (yy - y < trunkHeight) {
                r = 0;
            }
            else {
                r = topRadius;
            }
            for (int xx = x - r; xx <= x + r && free; ++xx) {
                for (int zz = z - r; zz <= z + r && free; ++zz) {
                    if (yy >= 0 && yy < Level.MAX_HEIGHT) {
                        final int tt = level.getTile(xx, yy, zz);
                        if (tt != 0 && tt != Block.leaves.id) free = false;
                    }
                    else {
                        free = false;
                    }
                }
            }
        }

        if (!free) return false;

        // must stand on ground
        final int belowTile = level.getTile(x, y - 1, z);
        if ((belowTile != Block.grass.id && belowTile != Block.dirt.id) || y >= Level.MAX_HEIGHT - treeHeight - 1) return false;

        level.setTileNoUpdate(x, y - 1, z, Block.dirt.id);

        // place leaf top
        int currentRadius = 0;
        for (int yy = y + treeHeight; yy >= y + trunkHeight; --yy) {
            for (int xx = x - currentRadius; xx <= x + currentRadius; ++xx) {
                final int xo = xx - x;
                for (int zz = z - currentRadius; zz <= z + currentRadius; ++zz) {
                    final int zo = zz - z;
                    if (Math.abs(xo) == currentRadius && Math.abs(zo) == currentRadius && currentRadius > 0) continue;
                    if (!Block.solid[level.getTile(xx, yy, zz)]) level.setTileAndDataNoUpdate(xx, yy, zz, Block.leaves.id, BlockLogicLeaves.EVERGREEN_LEAF);
                }
            }
            if (currentRadius >= 1 && yy == y + trunkHeight + 1) {
                currentRadius -= 1;
            }
            else if (currentRadius < topRadius) {
                currentRadius += 1;
            }
        }
        for (int hh = 0; hh < treeHeight - 1; ++hh) {
            final int t = level.getTile(x, y + hh, z);
            if (t == 0 || t == Block.leaves.id) level.setTileAndDataNoUpdate(x, y + hh, z, Block.treeTrunk.id, TreeBlock.DARK_TRUNK);
        }

        return true;
    }
}
