// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.levelgen.feature;

import net.minecraft.world.level.block.BlockLeaves;
import net.minecraft.world.level.block.Block;
import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BlockLog;
import net.minecraft.world.level.block.Blocks;

public class BirchFeature extends Feature
{
    @Override
    public boolean place(final Level level, final Random random, final int x, final int y, final int z) {
        final int treeHeight = random.nextInt(3) + 5;

        boolean free = true;
        if (y < 1 || y + treeHeight + 1 > Level.MAX_HEIGHT) return false;

        for (int yy = y; yy <= y + 1 + treeHeight; ++yy) {
            int r = 1;
            if (yy == y) r = 0;
            if (yy >= y + 1 + treeHeight - 2) r = 2;

            for (int xx = x - r; xx <= x + r && free; ++xx) {
                for (int zz = z - r; zz <= z + r && free; ++zz) {
                    if (yy >= 0 && yy < Level.MAX_HEIGHT) {
                        final int tt = level.getTile(xx, yy, zz);
                        if (tt != 0 && tt != Blocks.leaves.id) free = false;
                    }
                    else {
                        free = false;
                    }
                }
            }
        }

        if (!free) return false;

        final int belowTile = level.getTile(x, y - 1, z);
        if ((belowTile != Blocks.grass.id && belowTile != Blocks.dirt.id) || y >= Level.MAX_HEIGHT - treeHeight - 1) return false;

        level.setTileNoUpdate(x, y - 1, z, Blocks.dirt.id);
        for (int yy = y - 3 + treeHeight; yy <= y + treeHeight; ++yy) {
            final int yo = yy - (y + treeHeight);
            final int offs = 1 - yo / 2;
            for (int xx = x - offs; xx <= x + offs; ++xx) {
                final int xo = xx - x;
                for (int zz = z - offs; zz <= z + offs; ++zz) {
                    final int zo = zz - z;
                    if (Math.abs(xo) == offs && Math.abs(zo) == offs && (random.nextInt(2) == 0 || yo == 0)) continue;
                    if (!Block.solid[level.getTile(xx, yy, zz)]) level.setTileAndDataNoUpdate(xx, yy, zz, Blocks.leaves.id, BlockLeaves.BIRCH_LEAF);
                }
            }
        }

        for (int hh = 0; hh < treeHeight; ++hh) {
            final int t = level.getTile(x, y + hh, z);
            if (t == 0 || t == Blocks.leaves.id) level.setTileAndDataNoUpdate(x, y + hh, z, Blocks.treeTrunk.id, BlockLog.BIRCH_TRUNK);
        }
        return true;
    }
}
