// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client;

import net.minecraft.world.level.tile.Block;
import java.util.ArrayList;
import java.util.List;

public class User
{
    public static List<Block> allowedBlocks = new ArrayList<>();
    static {
        User.allowedBlocks.add(Block.rock);
        User.allowedBlocks.add(Block.stoneBrick);
        User.allowedBlocks.add(Block.redBrick);
        User.allowedBlocks.add(Block.dirt);
        User.allowedBlocks.add(Block.wood);
        User.allowedBlocks.add(Block.treeTrunk);
        User.allowedBlocks.add(Block.leaves);
        User.allowedBlocks.add(Block.torch);
        User.allowedBlocks.add(Block.stoneSlabHalf);
        User.allowedBlocks.add(Block.glass);
        User.allowedBlocks.add(Block.mossStone);
        User.allowedBlocks.add(Block.sapling);
        User.allowedBlocks.add(Block.flower);
        User.allowedBlocks.add(Block.rose);
        User.allowedBlocks.add(Block.mushroom1);
        User.allowedBlocks.add(Block.mushroom2);
        User.allowedBlocks.add(Block.sand);
        User.allowedBlocks.add(Block.gravel);
        User.allowedBlocks.add(Block.sponge);
        User.allowedBlocks.add(Block.cloth);
        User.allowedBlocks.add(Block.coalOre);
        User.allowedBlocks.add(Block.ironOre);
        User.allowedBlocks.add(Block.goldOre);
        User.allowedBlocks.add(Block.ironBlock);
        User.allowedBlocks.add(Block.goldBlock);
        User.allowedBlocks.add(Block.bookshelf);
        User.allowedBlocks.add(Block.tnt);
        User.allowedBlocks.add(Block.obsidian);
    }
    public String name;
    public String sessionId;

    public String mpPassword;

    public User(final String name, final String sessionId) {
        this.name = name;
        this.sessionId = sessionId;
    }
}
