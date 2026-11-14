package net.minecraft.world.level.block;

import net.minecraft.stats.Stats;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.tileentity.TileEntitySign;
import net.minecraft.world.level.material.Material;

public class Blocks {
    public static final Block rock = new BlockStone(1, 1)
            .setDestroyTime(1.5f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("stone");
    public static final Block button = new BlockButton(77, rock.tex)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("button")
            .sendTileData();
    public static final Block pressurePlate_stone = new BlockPressurePlate(70, rock.tex, BlockPressurePlate.Sensitivity.mobs, Material.stone)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("pressurePlate")
            .sendTileData();
    public static final BlockDirtGrassy grass = (BlockDirtGrassy) new BlockDirtGrassy(2)
            .setDestroyTime(0.6f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("grass");
    public static final Block dirt = new BlockDirt(3, 2)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_GRAVEL)
            .setDescriptionId("dirt");
    public static final Block stoneBrick = new Block(4, 16, Material.stone)
            .setDestroyTime(2.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("stonebrick");
    public static final Block stairs_stone = new BlockStairs(67, stoneBrick)
            .setDescriptionId("stairsStone")
            .sendTileData();
    public static final Block wood = new Block(5, 4, Material.wood)
            .setDestroyTime(2.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("wood")
            .sendTileData();
    public static final Block pressurePlate_wood = new BlockPressurePlate(72, wood.tex, BlockPressurePlate.Sensitivity.everything, Material.wood)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("pressurePlate")
            .sendTileData();
    public static final Block stairs_wood = new BlockStairs(53, wood)
            .setDescriptionId("stairsWood")
            .sendTileData();
    public static final Block sapling = new BlockSapling(6, 15)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("sapling")
            .sendTileData();
    public static final Block unbreakable = new Block(7, 17, Material.stone)
            .setIndestructible()
            .setExplodeable(6000000.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("bedrock")
            .setNotCollectStatistics();
    public static final Block water = new BlockFluidDynamic(8, Material.water)
            .setDestroyTime(100.0f)
            .setLightBlock(3)
            .setDescriptionId("water")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block calmWater = new BlockFluidStatic(9, Material.water)
            .setDestroyTime(100.0f)
            .setLightBlock(3)
            .setDescriptionId("water")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block lava = new BlockFluidDynamic(10, Material.lava)
            .setDestroyTime(0.0f)
            .setLightEmission(1.0f)
            .setLightBlock(255)
            .setDescriptionId("lava")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block calmLava = new BlockFluidStatic(11, Material.lava)
            .setDestroyTime(100.0f)
            .setLightEmission(1.0f)
            .setLightBlock(255)
            .setDescriptionId("lava")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block sand = new BlockFalling(12, 18)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_SAND)
            .setDescriptionId("sand");
    public static final Block gravel = new BlockGravel(13, 19)
            .setDestroyTime(0.6f)
            .setSoundType(Block.SOUND_GRAVEL)
            .setDescriptionId("gravel");
    public static final Block goldOre = new BlockOre(14, 32)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreGold");
    public static final Block ironOre = new BlockOre(15, 33)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreIron");
    public static final Block coalOre = new BlockOre(16, 34)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreCoal");
    public static final Block treeTrunk = new BlockLog(17)
            .setDestroyTime(2.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("log")
            .sendTileData();
    public static final BlockLeaves leaves = (BlockLeaves) new BlockLeaves(18, 52)
            .setDestroyTime(0.2f)
            .setLightBlock(1)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("leaves")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block sponge = new BlockSponge(19)
            .setDestroyTime(0.6f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("sponge");
    public static final Block glass = new BlockGlass(20, 49, Material.glass, false)
            .setDestroyTime(0.3f)
            .setSoundType(Block.SOUND_GLASS)
            .setDescriptionId("glass");
    public static final Block lapisOre = new BlockOre(21, 160)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreLapis");
    public static final Block lapisBlock = new Block(22, 144, Material.stone)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("blockLapis");
    public static final Block dispenser = new BlockDispenser(23)
            .setDestroyTime(3.5f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("dispenser")
            .sendTileData();
    public static final Block sandStone = new BlockSandstone(24)
            .setSoundType(Block.SOUND_STONE)
            .setDestroyTime(0.8f)
            .setDescriptionId("sandStone");
    public static final Block musicBlock = new BlockNoteblock(25)
            .setDestroyTime(0.8f)
            .setDescriptionId("musicBlock")
            .sendTileData();
    public static final Block bed = new BlockBed(26)
            .setDestroyTime(0.2f)
            .setDescriptionId("bed")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block goldenRail = new BlockRail(27, 179, true)
            .setDestroyTime(0.7f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("goldenRail")
            .sendTileData();
    public static final Block detectorRail = new BlockRailDetector(28, 195)
            .setDestroyTime(0.7f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("detectorRail")
            .sendTileData();
    public static final Block pistonStickyBase = new BlockPistonBase(29, BlockPistonBase.PLATFORM_STICKY_TEX, true)
            .setDescriptionId("pistonStickyBase")
            .sendTileData();
    public static final Block web = new BlockCobweb(30, 11)
            .setLightBlock(1)
            .setDestroyTime(4.0f)
            .setDescriptionId("web");
    public static final BlockGrass tallgrass = (BlockGrass) new BlockGrass(31, 39)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("tallgrass");
    public static final BlockDeadBush deadBush = (BlockDeadBush) new BlockDeadBush(32, 55)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("deadbush");
    public static final Block pistonBase = new BlockPistonBase(33, BlockPistonBase.PLATFORM_STICKY_TEX, false)
            .setDescriptionId("pistonBase")
            .sendTileData();
    public static final BlockPistonHead pistonExtension = (BlockPistonHead) new BlockPistonHead(34, 107)
            .sendTileData();
    public static final Block cloth = new BlockWool()
            .setDestroyTime(0.8f)
            .setSoundType(Block.SOUND_CLOTH)
            .setDescriptionId("cloth")
            .sendTileData();
    public static final BlockPistonMovingPiece pistonMovingPiece = new BlockPistonMovingPiece(36);
    public static final BlockBush flower = (BlockBush) new BlockBush(37, 13)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("flower");
    public static final BlockBush rose = (BlockBush) new BlockBush(38, 12)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("rose");
    public static final BlockBush mushroom1 = (BlockBush) new BlockMushroom(39, 29)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setLightEmission(0.125f)
            .setDescriptionId("mushroom");
    public static final BlockBush mushroom2 = (BlockBush) new BlockMushroom(40, 28)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("mushroom");
    public static final Block goldBlock = new BlockMetal(41, 23)
            .setDestroyTime(3.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("blockGold");
    public static final Block ironBlock = new BlockMetal(42, 22)
            .setDestroyTime(5.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("blockIron");
    public static final Block stoneSlab = new BlockSlab(43, true)
            .setDestroyTime(2.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("stoneSlab");
    public static final Block stoneSlabHalf = new BlockSlab(44, false)
            .setDestroyTime(2.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("stoneSlab");
    public static final Block redBrick = new Block(45, 7, Material.stone)
            .setDestroyTime(2.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("brick");
    public static final Block tnt = new BlockTNT(46, 8)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("tnt");
    public static final Block bookshelf = new BlockBookshelf(47, 35)
            .setDestroyTime(1.5f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("bookshelf");
    public static final Block mossStone = new Block(48, 36, Material.stone)
            .setDestroyTime(2.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("stoneMoss");
    public static final Block obsidian = new BlockObsidian(49, 37)
            .setDestroyTime(10.0f)
            .setExplodeable(2000.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("obsidian");
    public static final Block torch = new BlockTorch(50, 80)
            .setDestroyTime(0.0f)
            .setLightEmission(0.9375f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("torch")
            .sendTileData();
    public static final BlockFire fire = (BlockFire) new BlockFire(51, 31)
            .setDestroyTime(0.0f)
            .setLightEmission(1.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("fire")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block mobSpawner = new BlockMobSpawner(52, 65)
            .setDestroyTime(5.0f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("mobSpawner")
            .setNotCollectStatistics();
    public static final Block chest = new BlockChest(54)
            .setDestroyTime(2.5f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("chest")
            .sendTileData();
    public static final Block redStoneDust = new BlockRedstoneDust(55, 164)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_NORMAL)
            .setDescriptionId("redstoneDust")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block diamondOre = new BlockOre(56, 50)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreDiamond");
    public static final Block diamondBlock = new BlockMetal(57, 24)
            .setDestroyTime(5.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("blockDiamond");
    public static final Block workBench = new BlockWorkbench(58)
            .setDestroyTime(2.5f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("workbench");
    public static final Block crops = new BlockWheatCrop(59, 88)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("crops")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block farmland = new BlockFarmland(60)
            .setDestroyTime(0.6f)
            .setSoundType(Block.SOUND_GRAVEL)
            .setDescriptionId("farmland");
    public static final Block furnace = new BlockFurnace(61, false)
            .setDestroyTime(3.5f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("furnace")
            .sendTileData();
    public static final Block furnace_lit = new BlockFurnace(62, true)
            .setDestroyTime(3.5f)
            .setSoundType(Block.SOUND_STONE)
            .setLightEmission(0.875f)
            .setDescriptionId("furnace")
            .sendTileData();
    public static final Block sign = new BlockSign(63, TileEntitySign.class, true)
            .setDestroyTime(1.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("sign")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block door_wood = new BlockDoor(64, Material.wood)
            .setDestroyTime(3.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("doorWood")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block ladder = new BlockLadder(65, 83)
            .setDestroyTime(0.4f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("ladder")
            .sendTileData();
    public static final Block rail = new BlockRail(66, 128, false)
            .setDestroyTime(0.7f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("rail")
            .sendTileData();
    public static final Block wallSign = new BlockSign(68, TileEntitySign.class, false)
            .setDestroyTime(1.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("sign")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block lever = new BlockLever(69, 96)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("lever")
            .sendTileData();
    public static final Block door_iron = new BlockDoor(71, Material.metal)
            .setDestroyTime(5.0f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("doorIron")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block redStoneOre = new BlockRedstoneOre(73, 51, false)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreRedstone")
            .sendTileData();
    public static final Block redStoneOre_lit = new BlockRedstoneOre(74, 51, true)
            .setLightEmission(0.625f)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreRedstone")
            .sendTileData();
    public static final Block notGate_off = new BlockRedstoneTorch(75, 115, false)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("notGate")
            .sendTileData();
    public static final Block notGate_on = new BlockRedstoneTorch(76, 99, true)
            .setDestroyTime(0.0f)
            .setLightEmission(0.5f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("notGate")
            .sendTileData();
    public static final Block topSnow = new BlockSnowLayer(78, 66)
            .setDestroyTime(0.1f)
            .setSoundType(Block.SOUND_CLOTH)
            .setDescriptionId("snow");
    public static final Block ice = new BlockIce(79, 67)
            .setDestroyTime(0.5f)
            .setLightBlock(3)
            .setSoundType(Block.SOUND_GLASS)
            .setDescriptionId("ice");
    public static final Block snow = new BlockSnow(80, 66)
            .setDestroyTime(0.2f)
            .setSoundType(Block.SOUND_CLOTH)
            .setDescriptionId("snow");
    public static final Block cactus = new BlockCactus(81, 70)
            .setDestroyTime(0.4f)
            .setSoundType(Block.SOUND_CLOTH)
            .setDescriptionId("cactus");
    public static final Block clay = new BlockClay(82, 72)
            .setDestroyTime(0.6f)
            .setSoundType(Block.SOUND_GRAVEL)
            .setDescriptionId("clay");
    public static final Block reeds = new BlockSugarcane(83, 73)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("reeds")
            .setNotCollectStatistics();
    public static final Block recordPlayer = new BlockJukebox(84, 74)
            .setDestroyTime(2.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("jukebox")
            .sendTileData();
    public static final Block fence = new BlockFence(85, 4)
            .setDestroyTime(2.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("fence")
            .sendTileData();
    public static final Block pumpkin = new BlockPumpkin(86, 102, false)
            .setDestroyTime(1.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("pumpkin")
            .sendTileData();
    public static final Block hellRock = new BlockNetherrack(87, 103)
            .setDestroyTime(0.4f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("hellrock");
    public static final Block hellSand = new BlockSoulSand(88, 104)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_SAND)
            .setDescriptionId("hellsand");
    public static final Block lightGem = new BlockGlowstone(89, 105, Material.stone)
            .setDestroyTime(0.3f)
            .setSoundType(Block.SOUND_GLASS)
            .setLightEmission(1.0f)
            .setDescriptionId("lightgem");
    public static final BlockPortalNether portalTile = (BlockPortalNether) new BlockPortalNether(90, 14)
            .setDestroyTime(-1.0f)
            .setSoundType(Block.SOUND_GLASS)
            .setLightEmission(0.75f)
            .setDescriptionId("portal");
    public static final Block litPumpkin = new BlockPumpkin(91, 102, true)
            .setDestroyTime(1.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setLightEmission(1.0f)
            .setDescriptionId("litpumpkin")
            .sendTileData();
    public static final Block cake = new BlockCake(92, 121)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_CLOTH)
            .setDescriptionId("cake")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block diode_off = new BlockRedstoneRepeater(93, false)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("diode")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block diode_on = new BlockRedstoneRepeater(94, true)
            .setDestroyTime(0.0f)
            .setLightEmission(0.625f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("diode")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block aprilFoolsJoke = new BlockLockedChest(95)
            .setDestroyTime(0.0f)
            .setLightEmission(1.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("lockedchest")
            .setTicking(true)
            .sendTileData();
    public static final Block trapdoor = new BlockTrapdoor(96, Material.wood)
            .setDestroyTime(3.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("trapdoor")
            .setNotCollectStatistics()
            .sendTileData();

    static {
        Item.items[Blocks.cloth.id] = new ItemBlockCloth(Blocks.cloth.id - Block.TILE_NUM_COUNT).setDescriptionId("cloth");
        Item.items[Blocks.treeTrunk.id] = new ItemBlockLog(Blocks.treeTrunk.id - Block.TILE_NUM_COUNT).setDescriptionId("log");
        Item.items[Blocks.stoneSlabHalf.id] = new ItemBlockSlabStone(Blocks.stoneSlabHalf.id - Block.TILE_NUM_COUNT).setDescriptionId("stoneSlab");
        Item.items[Blocks.sapling.id] = new ItemBlockSapling(Blocks.sapling.id - Block.TILE_NUM_COUNT).setDescriptionId("sapling");
        Item.items[Blocks.leaves.id] = new ItemBlockLeaves(Blocks.leaves.id - Block.TILE_NUM_COUNT).setDescriptionId("leaves");
        Item.items[Blocks.pistonBase.id] = new ItemBlockPiston(Blocks.pistonBase.id - Block.TILE_NUM_COUNT);
        Item.items[Blocks.pistonStickyBase.id] = new ItemBlockPiston(Blocks.pistonStickyBase.id - Block.TILE_NUM_COUNT);
        for (int i = 0; i < Block.TILE_NUM_COUNT; ++i) {
            if (Block.BLOCKS[i] != null && Item.items[i] == null) {
                Item.items[i] = new ItemBlock(i - Block.TILE_NUM_COUNT);
                Block.BLOCKS[i].init();
            }
        }
        Block.transculent[0] = true;
        Stats.buildBlockStats();
    }
}
