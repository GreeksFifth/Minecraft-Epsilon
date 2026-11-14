// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class User
{
    public static List<Block> allowedBlocks = new ArrayList<>();
    static {
        User.allowedBlocks.add(Blocks.rock);
        User.allowedBlocks.add(Blocks.stoneBrick);
        User.allowedBlocks.add(Blocks.redBrick);
        User.allowedBlocks.add(Blocks.dirt);
        User.allowedBlocks.add(Blocks.wood);
        User.allowedBlocks.add(Blocks.treeTrunk);
        User.allowedBlocks.add(Blocks.leaves);
        User.allowedBlocks.add(Blocks.torch);
        User.allowedBlocks.add(Blocks.stoneSlabHalf);
        User.allowedBlocks.add(Blocks.glass);
        User.allowedBlocks.add(Blocks.mossStone);
        User.allowedBlocks.add(Blocks.sapling);
        User.allowedBlocks.add(Blocks.flower);
        User.allowedBlocks.add(Blocks.rose);
        User.allowedBlocks.add(Blocks.mushroom1);
        User.allowedBlocks.add(Blocks.mushroom2);
        User.allowedBlocks.add(Blocks.sand);
        User.allowedBlocks.add(Blocks.gravel);
        User.allowedBlocks.add(Blocks.sponge);
        User.allowedBlocks.add(Blocks.cloth);
        User.allowedBlocks.add(Blocks.coalOre);
        User.allowedBlocks.add(Blocks.ironOre);
        User.allowedBlocks.add(Blocks.goldOre);
        User.allowedBlocks.add(Blocks.ironBlock);
        User.allowedBlocks.add(Blocks.goldBlock);
        User.allowedBlocks.add(Blocks.bookshelf);
        User.allowedBlocks.add(Blocks.tnt);
        User.allowedBlocks.add(Blocks.obsidian);
    }
    public String name;
    public String sessionId;

    public String mpPassword;

    public User(final String name, final String sessionId) {
        this.name = name;
        this.sessionId = sessionId;
    }
}
