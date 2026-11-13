// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.tile;

import net.minecraft.Facing;
import net.minecraft.locale.Descriptive;
import net.minecraft.world.item.TileItem;
import net.minecraft.world.item.PistonTileItem;
import net.minecraft.world.item.LeavesTileItem;
import net.minecraft.world.item.SaplingTileItem;
import net.minecraft.world.item.StoneSlabTileItem;
import net.minecraft.world.item.TreeTileItem;
import net.minecraft.world.item.ClothTileItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.tile.entity.SignTileEntity;
import net.minecraft.locale.language.I18n;
import net.minecraft.world.entity.Mob;
import net.minecraft.stats.Stats;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.entity.player.Player;
import java.util.Random;
import java.util.ArrayList;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelSource;
import net.minecraft.world.level.material.Material;

public class Block implements Descriptive<Block> {
    public static final int TILE_NUM_COUNT = 256;
    public static final int TILE_NUM_MASK = 0xff; // 256 - 1
    public static final int TILE_NUM_SHIFT = 8; // 256 is 8 bits

    private static final String TILE_DESCRIPTION_PREFIX = "tile.";
    public static final float INDESTRUCTIBLE_DESTROY_TIME = -1.0f;
    public static final SoundType SOUND_NORMAL = new SoundType("stone", 1.0f, 1.0f);
    public static final SoundType SOUND_WOOD = new SoundType("wood", 1.0f, 1.0f);
    public static final SoundType SOUND_GRAVEL = new SoundType("gravel", 1.0f, 1.0f);
    public static final SoundType SOUND_GRASS = new SoundType("grass", 1.0f, 1.0f);
    public static final SoundType SOUND_STONE = new SoundType("stone", 1.0f, 1.0f);
    public static final SoundType SOUND_METAL = new SoundType("stone", 1.0f, 1.5f);
    public static final SoundType SOUND_GLASS = new SoundType("stone", 1.0f, 1.0f) {
        @Override
        public String getBreakSound() {
            return "random.glass";
        }
    };
    public static final SoundType SOUND_CLOTH = new SoundType("cloth", 1.0f, 1.0f);
    public static final SoundType SOUND_SAND = new SoundType("sand", 1.0f, 1.0f) {
        @Override
        public String getBreakSound() {
            return "step.gravel";
        }
    };

    public static final int SHAPE_INVISIBLE = -1;
    public static final int SHAPE_BLOCK = 0;
    public static final int SHAPE_CROSS_TEXTURE = 1;
    public static final int SHAPE_TORCH = 2;
    public static final int SHAPE_FIRE = 3;
    public static final int SHAPE_WATER = 4;
    public static final int SHAPE_RED_DUST = 5;
    public static final int SHAPE_ROWS = 6;
    public static final int SHAPE_DOOR = 7;
    public static final int SHAPE_LADDER = 8;
    public static final int SHAPE_RAIL = 9;
    public static final int SHAPE_STAIRS = 10;
    public static final int SHAPE_FENCE = 11;
    public static final int SHAPE_LEVER = 12;
    public static final int SHAPE_CACTUS = 13;
    public static final int SHAPE_BED = 14;
    public static final int SHAPE_DIODE = 15;
    public static final int SHAPE_PISTON_BASE = 16;
    public static final int SHAPE_PISTON_EXTENSION = 17;

    public static final Block[] BLOCKS = new Block[TILE_NUM_COUNT];
    public static final boolean[] shouldTick = new boolean[TILE_NUM_COUNT];
    public static final boolean[] solid = new boolean[TILE_NUM_COUNT];
    public static final boolean[] isEntityTile = new boolean[TILE_NUM_COUNT];
    public static final int[] lightBlock = new int[TILE_NUM_COUNT];
    public static final boolean[] transculent = new boolean[TILE_NUM_COUNT];
    public static final int[] lightEmission = new int[TILE_NUM_COUNT];
    public static final boolean[] sendTileData = new boolean[TILE_NUM_COUNT];

    public static final Block rock = new StoneBlock(1, 1)
            .setDestroyTime(1.5f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("stone");
    public static final BlockLogicGrass grass = (BlockLogicGrass)new BlockLogicGrass(2)
            .setDestroyTime(0.6f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("grass");
    public static final Block dirt = new BlockLogicDirt(3, 2)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_GRAVEL)
            .setDescriptionId("dirt");
    public static final Block stoneBrick = new Block(4, 16, Material.stone)
            .setDestroyTime(2.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("stonebrick");
    public static final Block wood = new Block(5, 4, Material.wood)
            .setDestroyTime(2.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("wood")
            .sendTileData();
    public static final Block sapling = new Sapling(6, 15)
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
    public static final Block water = new BlockLogicFluidDynamic(8, Material.water)
            .setDestroyTime(100.0f)
            .setLightBlock(3)
            .setDescriptionId("water")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block calmWater = new BlockLogicFluidStatic(9, Material.water)
            .setDestroyTime(100.0f)
            .setLightBlock(3)
            .setDescriptionId("water")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block lava = new BlockLogicFluidDynamic(10, Material.lava)
            .setDestroyTime(0.0f)
            .setLightEmission(1.0f)
            .setLightBlock(255)
            .setDescriptionId("lava")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block calmLava = new BlockLogicFluidStatic(11, Material.lava)
            .setDestroyTime(100.0f)
            .setLightEmission(1.0f)
            .setLightBlock(255)
            .setDescriptionId("lava")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block sand = new SandBlock(12, 18)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_SAND)
            .setDescriptionId("sand");
    public static final Block gravel = new BlockLogicGravel(13, 19)
            .setDestroyTime(0.6f)
            .setSoundType(Block.SOUND_GRAVEL)
            .setDescriptionId("gravel");
    public static final Block goldOre = new OreBlock(14, 32)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreGold");
    public static final Block ironOre = new OreBlock(15, 33)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreIron");
    public static final Block coalOre = new OreBlock(16, 34)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreCoal");
    public static final Block treeTrunk = new TreeBlock(17)
            .setDestroyTime(2.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("log")
            .sendTileData();
    public static final BlockLogicLeaves leaves = (BlockLogicLeaves)new BlockLogicLeaves(18, 52)
            .setDestroyTime(0.2f)
            .setLightBlock(1)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("leaves")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block sponge = new Sponge(19)
            .setDestroyTime(0.6f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("sponge");
    public static final Block glass = new BlockLogicGlass(20, 49, Material.glass, false)
            .setDestroyTime(0.3f)
            .setSoundType(Block.SOUND_GLASS)
            .setDescriptionId("glass");
    public static final Block lapisOre = new OreBlock(21, 160)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreLapis");
    public static final Block lapisBlock = new Block(22, 144, Material.stone)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("blockLapis");
    public static final Block dispenser = new BlockLogicDispenser(23)
            .setDestroyTime(3.5f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("dispenser")
            .sendTileData();
    public static final Block sandStone = new SandStoneBlock(24)
            .setSoundType(Block.SOUND_STONE)
            .setDestroyTime(0.8f)
            .setDescriptionId("sandStone");
    public static final Block musicBlock = new MusicBlock(25)
            .setDestroyTime(0.8f)
            .setDescriptionId("musicBlock")
            .sendTileData();
    public static final Block bed = new BlockLogicBed(26)
            .setDestroyTime(0.2f)
            .setDescriptionId("bed")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block goldenRail = new RailBlock(27, 179, true)
            .setDestroyTime(0.7f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("goldenRail")
            .sendTileData();
    public static final Block detectorRail = new BlockLogicRailDetector(28, 195)
            .setDestroyTime(0.7f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("detectorRail")
            .sendTileData();
    public static final Block pistonStickyBase = new PistonBaseBlock(29, PistonBaseBlock.PLATFORM_STICKY_TEX, true)
            .setDescriptionId("pistonStickyBase")
            .sendTileData();
    public static final Block web = new WebBlock(30, 11)
            .setLightBlock(1)
            .setDestroyTime(4.0f)
            .setDescriptionId("web");
    public static final TallGrass tallgrass = (TallGrass)new TallGrass(31, 39)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("tallgrass");
    public static final BlockLogicDeadBush deadBush = (BlockLogicDeadBush)new BlockLogicDeadBush(32, 55)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("deadbush");
    public static final Block pistonBase = new PistonBaseBlock(33, PistonBaseBlock.PLATFORM_STICKY_TEX, false)
            .setDescriptionId("pistonBase")
            .sendTileData();
    public static final PistonExtensionBlock pistonExtension = (PistonExtensionBlock)new PistonExtensionBlock(34, 107)
            .sendTileData();
    public static final Block cloth = new BlockLogicWool()
            .setDestroyTime(0.8f)
            .setSoundType(Block.SOUND_CLOTH)
            .setDescriptionId("cloth")
            .sendTileData();
    public static final PistonMovingPiece pistonMovingPiece = new PistonMovingPiece(36);
    public static final BlockLogicBush flower = (BlockLogicBush)new BlockLogicBush(37, 13)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("flower");
    public static final BlockLogicBush rose = (BlockLogicBush)new BlockLogicBush(38, 12)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("rose");
    public static final BlockLogicBush mushroom1 = (BlockLogicBush)new Mushroom(39, 29)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setLightEmission(0.125f)
            .setDescriptionId("mushroom");
    public static final BlockLogicBush mushroom2 = (BlockLogicBush)new Mushroom(40, 28)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("mushroom");
    public static final Block goldBlock = new MetalBlock(41, 23)
            .setDestroyTime(3.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("blockGold");
    public static final Block ironBlock = new MetalBlock(42, 22)
            .setDestroyTime(5.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("blockIron");
    public static final Block stoneSlab = new StoneSlabBlock(43, true)
            .setDestroyTime(2.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("stoneSlab");
    public static final Block stoneSlabHalf = new StoneSlabBlock(44, false)
            .setDestroyTime(2.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("stoneSlab");
    public static final Block redBrick = new Block(45, 7, Material.stone)
            .setDestroyTime(2.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("brick");
    public static final Block tnt = new TntBlock(46, 8)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("tnt");
    public static final Block bookshelf = new BlockLogicBookshelf(47, 35)
            .setDestroyTime(1.5f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("bookshelf");
    public static final Block mossStone = new Block(48, 36, Material.stone)
            .setDestroyTime(2.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("stoneMoss");
    public static final Block obsidian = new ObsidianBlock(49, 37)
            .setDestroyTime(10.0f)
            .setExplodeable(2000.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("obsidian");
    public static final Block torch = new TorchBlock(50, 80)
            .setDestroyTime(0.0f)
            .setLightEmission(0.9375f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("torch")
            .sendTileData();
    public static final BlockLogicFire fire = (BlockLogicFire)new BlockLogicFire(51, 31)
            .setDestroyTime(0.0f)
            .setLightEmission(1.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("fire")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block mobSpawner = new MobSpawnerBlock(52, 65)
            .setDestroyTime(5.0f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("mobSpawner")
            .setNotCollectStatistics();
    public static final Block stairs_wood = new StairBlock(53, Block.wood)
            .setDescriptionId("stairsWood")
            .sendTileData();
    public static final Block chest = new BlockLogicChest(54)
            .setDestroyTime(2.5f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("chest")
            .sendTileData();
    public static final Block redStoneDust = new RedStoneDustBlock(55, 164)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_NORMAL)
            .setDescriptionId("redstoneDust")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block diamondOre = new OreBlock(56, 50)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreDiamond");
    public static final Block diamondBlock = new MetalBlock(57, 24)
            .setDestroyTime(5.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("blockDiamond");
    public static final Block workBench = new WorkbenchBlock(58)
            .setDestroyTime(2.5f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("workbench");
    public static final Block crops = new BlockLogicWheatCrop(59, 88)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("crops")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block farmland = new BlockLogicFarmland(60)
            .setDestroyTime(0.6f)
            .setSoundType(Block.SOUND_GRAVEL)
            .setDescriptionId("farmland");
    public static final Block furnace = new BlockLogicFurnace(61, false)
            .setDestroyTime(3.5f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("furnace")
            .sendTileData();
    public static final Block furnace_lit = new BlockLogicFurnace(62, true)
            .setDestroyTime(3.5f)
            .setSoundType(Block.SOUND_STONE)
            .setLightEmission(0.875f)
            .setDescriptionId("furnace")
            .sendTileData();
    public static final Block sign = new SignBlock(63, SignTileEntity.class, true)
            .setDestroyTime(1.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("sign")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block door_wood = new BlockLogicDoor(64, Material.wood)
            .setDestroyTime(3.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("doorWood")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block ladder = new BlockLogicLadder(65, 83)
            .setDestroyTime(0.4f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("ladder")
            .sendTileData();
    public static final Block rail = new RailBlock(66, 128, false)
            .setDestroyTime(0.7f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("rail")
            .sendTileData();
    public static final Block stairs_stone = new StairBlock(67, Block.stoneBrick)
            .setDescriptionId("stairsStone")
            .sendTileData();
    public static final Block wallSign = new SignBlock(68, SignTileEntity.class, false)
            .setDestroyTime(1.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("sign")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block lever = new BlockLogicLever(69, 96)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("lever")
            .sendTileData();
    public static final Block pressurePlate_stone = new PressurePlateBlock(70, Block.rock.tex, PressurePlateBlock.Sensitivity.mobs, Material.stone)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("pressurePlate")
            .sendTileData();
    public static final Block door_iron = new BlockLogicDoor(71, Material.metal)
            .setDestroyTime(5.0f)
            .setSoundType(Block.SOUND_METAL)
            .setDescriptionId("doorIron")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block pressurePlate_wood = new PressurePlateBlock(72, Block.wood.tex, PressurePlateBlock.Sensitivity.everything, Material.wood)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("pressurePlate")
            .sendTileData();
    public static final Block redStoneOre = new RedStoneOreBlock(73, 51, false)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreRedstone")
            .sendTileData();
    public static final Block redStoneOre_lit = new RedStoneOreBlock(74, 51, true)
            .setLightEmission(0.625f)
            .setDestroyTime(3.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("oreRedstone")
            .sendTileData();
    public static final Block notGate_off = new NotGateBlock(75, 115, false)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("notGate")
            .sendTileData();
    public static final Block notGate_on = new NotGateBlock(76, 99, true)
            .setDestroyTime(0.0f)
            .setLightEmission(0.5f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("notGate")
            .sendTileData();
    public static final Block button = new BlockLogicButton(77, Block.rock.tex)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("button")
            .sendTileData();
    public static final Block topSnow = new TopSnowBlock(78, 66)
            .setDestroyTime(0.1f)
            .setSoundType(Block.SOUND_CLOTH)
            .setDescriptionId("snow");
    public static final Block ice = new BlockLogicIce(79, 67)
            .setDestroyTime(0.5f)
            .setLightBlock(3)
            .setSoundType(Block.SOUND_GLASS)
            .setDescriptionId("ice");
    public static final Block snow = new SnowBlock(80, 66)
            .setDestroyTime(0.2f)
            .setSoundType(Block.SOUND_CLOTH)
            .setDescriptionId("snow");
    public static final Block cactus = new BlockLogicCactus(81, 70)
            .setDestroyTime(0.4f)
            .setSoundType(Block.SOUND_CLOTH)
            .setDescriptionId("cactus");
    public static final Block clay = new BlockLogicClay(82, 72)
            .setDestroyTime(0.6f)
            .setSoundType(Block.SOUND_GRAVEL)
            .setDescriptionId("clay");
    public static final Block reeds = new ReedBlock(83, 73)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_GRASS)
            .setDescriptionId("reeds")
            .setNotCollectStatistics();
    public static final Block recordPlayer = new RecordPlayerBlock(84, 74)
            .setDestroyTime(2.0f)
            .setExplodeable(10.0f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("jukebox")
            .sendTileData();
    public static final Block fence = new BlockLogicFence(85, 4)
            .setDestroyTime(2.0f)
            .setExplodeable(5.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("fence")
            .sendTileData();
    public static final Block pumpkin = new PumpkinBlock(86, 102, false)
            .setDestroyTime(1.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("pumpkin")
            .sendTileData();
    public static final Block hellRock = new BlockLogicNetherrack(87, 103)
            .setDestroyTime(0.4f)
            .setSoundType(Block.SOUND_STONE)
            .setDescriptionId("hellrock");
    public static final Block hellSand = new BlockLogicSoulSand(88, 104)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_SAND)
            .setDescriptionId("hellsand");
    public static final Block lightGem = new BlockLogicGlowstone(89, 105, Material.stone)
            .setDestroyTime(0.3f)
            .setSoundType(Block.SOUND_GLASS)
            .setLightEmission(1.0f)
            .setDescriptionId("lightgem");
    public static final PortalBlock portalTile = (PortalBlock)new PortalBlock(90, 14)
            .setDestroyTime(-1.0f)
            .setSoundType(Block.SOUND_GLASS)
            .setLightEmission(0.75f)
            .setDescriptionId("portal");
    public static final Block litPumpkin = new PumpkinBlock(91, 102, true)
            .setDestroyTime(1.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setLightEmission(1.0f)
            .setDescriptionId("litpumpkin")
            .sendTileData();
    public static final Block cake = new BlockLogicCake(92, 121)
            .setDestroyTime(0.5f)
            .setSoundType(Block.SOUND_CLOTH)
            .setDescriptionId("cake")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block diode_off = new BlockLogicRepeater(93, false)
            .setDestroyTime(0.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("diode")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block diode_on = new BlockLogicRepeater(94, true)
            .setDestroyTime(0.0f)
            .setLightEmission(0.625f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("diode")
            .setNotCollectStatistics()
            .sendTileData();
    public static final Block aprilFoolsJoke = new LockedChestBlock(95)
            .setDestroyTime(0.0f)
            .setLightEmission(1.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("lockedchest")
            .setTicking(true)
            .sendTileData();
    public static final Block trapdoor = new TrapDoorBlock(96, Material.wood)
            .setDestroyTime(3.0f)
            .setSoundType(Block.SOUND_WOOD)
            .setDescriptionId("trapdoor")
            .setNotCollectStatistics()
            .sendTileData();

    public int tex;
    public final int id;
    protected float destroySpeed;
    protected float explosionResistance;
    protected boolean isInventoryItem = true;
    protected boolean collectStatistics = true;
    public double xx0, yy0, zz0, xx1, yy1, zz1;
    public SoundType soundType = Block.SOUND_NORMAL;
    public float gravity = 1.0f;
    public final Material material;
    public float friction = 0.6f;
    private String descriptionId;
    
    protected Block(final int id, final Material material) {
        if (Block.BLOCKS[id] != null) throw new IllegalArgumentException("Slot " + id + " is already occupied by " + Block.BLOCKS[id] + " when adding " + this);
        this.material = material;
        Block.BLOCKS[id] = this;
        this.id = id;
        this.setShape(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        Block.solid[id] = this.isSolidRender();
        Block.lightBlock[id] = (this.isSolidRender() ? 255 : 0);
        Block.transculent[id] = !material.blocksLight();
        Block.isEntityTile[id] = false;
    }
    
    protected Block sendTileData() {
        Block.sendTileData[this.id] = true;
        return this;
    }
    
    protected void init() {
    }
    
    protected Block(final int id, final int tex, final Material material) {
        this(id, material);
        this.tex = tex;
    }
    
    protected Block setSoundType(final SoundType soundType) {
        this.soundType = soundType;
        return this;
    }
    
    protected Block setLightBlock(final int i) {
        Block.lightBlock[this.id] = i;
        return this;
    }
    
    protected Block setLightEmission(final float f) {
        Block.lightEmission[this.id] = (int)(Level.MAX_BRIGHTNESS * f);
        return this;
    }
    
    protected Block setExplodeable(final float explosionResistance) {
        this.explosionResistance = explosionResistance * 3.0f;
        return this;
    }
    
    public boolean isCubeShaped() {
        return true;
    }
    
    public int getRenderShape() {
        return Block.SHAPE_BLOCK;
    }
    
    protected Block setDestroyTime(final float destroySpeed) {
        this.destroySpeed = destroySpeed;
        if (this.explosionResistance < destroySpeed * 5.0f) this.explosionResistance = destroySpeed * 5.0f;
        return this;
    }
    
    protected Block setIndestructible() {
        this.setDestroyTime(INDESTRUCTIBLE_DESTROY_TIME);
        return this;
    }
    
    public float getDestroySpeed() {
        return this.destroySpeed;
    }
    
    protected Block setTicking(final boolean tick) {
        Block.shouldTick[this.id] = tick;
        return this;
    }
    
    public void setShape(final float x0, final float y0, final float z0, final float x1, final float y1, final float z1) {
        this.xx0 = x0;
        this.yy0 = y0;
        this.zz0 = z0;
        this.xx1 = x1;
        this.yy1 = y1;
        this.zz1 = z1;
    }
    
    public float getBrightness(final LevelSource level, final int x, final int y, final int z) {
        return level.getBrightness(x, y, z, Block.lightEmission[this.id]);
    }

    // Useless - Exists in b1.2 and LCE leaks
    public static boolean isFaceVisible(Level level, int x, int y, int z, int f) {
        if (f == Facing.DOWN) y--;
        if (f == Facing.UP) y++;
        if (f == Facing.NORTH) z--;
        if (f == Facing.SOUTH) z++;
        if (f == Facing.WEST) x--;
        if (f == Facing.EAST) x++;
        return !level.isSolidRenderTile(x, y, z);
    }

    public boolean shouldRenderFace(final LevelSource level, final int x, final int y, final int z, final int f) {
        if (f == Facing.DOWN && this.yy0 > 0.0) return true;
        if (f == Facing.UP && this.yy1 < 1.0) return true;
        if (f == Facing.NORTH && this.zz0 > 0.0) return true;
        if (f == Facing.SOUTH && this.zz1 < 1.0) return true;
        if (f == Facing.WEST && this.xx0 > 0.0) return true;
        if (f == Facing.EAST && this.xx1 < 1.0) return true;
        return !level.isSolidRenderTile(x, y, z);
    }
    
    public boolean isSolidFace(final LevelSource level, final int x, final int y, final int z, final int face) {
        return level.getMaterial(x, y, z).isSolid();
    }
    
    public int getTexture(final LevelSource level, final int x, final int y, final int z, final int face) {
        return this.getTexture(face, level.getData(x, y, z));
    }
    
    public int getTexture(final int face, final int data) {
        return this.getTexture(face);
    }
    
    public int getTexture(final int face) {
        return this.tex;
    }
    
    public AABB getTileAABB(final Level level, final int x, final int y, final int z) {
        return AABB.newTemp(x + this.xx0, y + this.yy0, z + this.zz0, x + this.xx1, y + this.yy1, z + this.zz1);
    }
    
    public void addAABBs(final Level level, final int x, final int y, final int z, final AABB box, final ArrayList<AABB> boxes) {
        final AABB aabb = this.getAABB(level, x, y, z);
        if (aabb != null && box.intersects(aabb)) boxes.add(aabb);
    }
    
    public AABB getAABB(final Level level, final int x, final int y, final int z) {
        return AABB.newTemp(x + this.xx0, y + this.yy0, z + this.zz0, x + this.xx1, y + this.yy1, z + this.zz1);
    }
    
    public boolean isSolidRender() {
        return true;
    }
    
    public boolean mayPick(final int data, final boolean liquid) {
        return this.mayPick();
    }
    
    public boolean mayPick() {
        return true;
    }
    
    public void tick(final Level level, final int x, final int y, final int z, final Random random) {
    }
    
    public void animateTick(final Level level, final int x, final int y, final int z, final Random random) {
    }
    
    public void destroy(final Level level, final int x, final int y, final int z, final int data) {
    }
    
    public void neighborChanged(final Level level, final int x, final int y, final int z, final int type) {
    }
    
    public int getTickDelay() {
        return 10;
    }
    
    public void onPlace(final Level level, final int x, final int y, final int z) {
    }
    
    public void onRemove(final Level level, final int x, final int y, final int z) {
    }
    
    public int getResourceCount(final Random random) {
        return 1;
    }
    
    public int getResource(final int data, final Random random) {
        return this.id;
    }
    
    public float getDestroyProgress(final Player player) {
        if (this.destroySpeed < 0.0f) return 0.0f;
        if (!player.canDestroy(this)) return 1.0f / this.destroySpeed / 100.0f;
        return player.getDestroySpeed(this) / this.destroySpeed / 30.0f;
    }
    
    public final void spawnResources(final Level level, final int x, final int y, final int z, final int data) {
        this.spawnResources(level, x, y, z, data, 1.0f);
    }
    
    public void spawnResources(final Level level, final int x, final int y, final int z, final int data, final float odds) {
        if (level.isClientSide) return;
        int count = this.getResourceCount(level.random);
        for (int i = 0; i < count; ++i) {
            if (level.random.nextFloat() > odds) continue;
            final int type = this.getResource(data, level.random);
            if (type <= 0) continue;

            this.popResource(level, x, y, z, new ItemInstance(type, 1, this.getSpawnResourcesAuxValue(data)));
        }
    }
    
    protected void popResource(final Level level, final int x, final int y, final int z, final ItemInstance itemInstance) {
        if (level.isClientSide) return;

        final float s = 0.7f;
        double xo = level.random.nextFloat() * s + (1.0f - s) * 0.5;
        double yo = level.random.nextFloat() * s + (1.0f - s) * 0.5;
        double zo = level.random.nextFloat() * s + (1.0f - s) * 0.5;
        final ItemEntity item = new ItemEntity(level, x + xo, y + yo, z + zo, itemInstance);
        item.throwTime = 10;
        level.addEntity(item);
    }
    
    protected int getSpawnResourcesAuxValue(final int data) {
        return 0;
    }
    
    public float getExplosionResistance(final Entity source) {
        return this.explosionResistance / 5.0f;
    }
    
    public HitResult clip(final Level level, final int xt, final int yt, final int zt, Vec3 a, Vec3 b) {
        this.updateShape(level, xt, yt, zt);

        a = a.add(-xt, -yt, -zt);
        b = b.add(-xt, -yt, -zt);

        Vec3 xh0 = a.clipX(b, this.xx0);
        Vec3 xh1 = a.clipX(b, this.xx1);

        Vec3 yh0 = a.clipY(b, this.yy0);
        Vec3 yh1 = a.clipY(b, this.yy1);

        Vec3 zh0 = a.clipZ(b, this.zz0);
        Vec3 zh1 = a.clipZ(b, this.zz1);

        if (!this.containsX(xh0)) xh0 = null;
        if (!this.containsX(xh1)) xh1 = null;
        if (!this.containsY(yh0)) yh0 = null;
        if (!this.containsY(yh1)) yh1 = null;
        if (!this.containsZ(zh0)) zh0 = null;
        if (!this.containsZ(zh1)) zh1 = null;

        Vec3 closest = null;
        if (xh0 != null && (closest == null || a.distanceTo(xh0) < a.distanceTo(closest))) closest = xh0;
        if (xh1 != null && (closest == null || a.distanceTo(xh1) < a.distanceTo(closest))) closest = xh1;
        if (yh0 != null && (closest == null || a.distanceTo(yh0) < a.distanceTo(closest))) closest = yh0;
        if (yh1 != null && (closest == null || a.distanceTo(yh1) < a.distanceTo(closest))) closest = yh1;
        if (zh0 != null && (closest == null || a.distanceTo(zh0) < a.distanceTo(closest))) closest = zh0;
        if (zh1 != null && (closest == null || a.distanceTo(zh1) < a.distanceTo(closest))) closest = zh1;

        if (closest == null) return null;

        int face = -1;
        if (closest == xh0) face = Facing.WEST;
        if (closest == xh1) face = Facing.EAST;
        if (closest == yh0) face = Facing.DOWN;
        if (closest == yh1) face = Facing.UP;
        if (closest == zh0) face = Facing.NORTH;
        if (closest == zh1) face = Facing.SOUTH;

        return new HitResult(xt, yt, zt, face, closest.add(xt, yt, zt));
    }
    
    private boolean containsX(final Vec3 v) {
        if (v == null) return false;
        return v.y >= this.yy0 && v.y <= this.yy1 && v.z >= this.zz0 && v.z <= this.zz1;
    }
    
    private boolean containsY(final Vec3 v) {
        if (v == null) return false;
        return v.x >= this.xx0 && v.x <= this.xx1 && v.z >= this.zz0 && v.z <= this.zz1;
    }
    
    private boolean containsZ(final Vec3 v) {
        if (v == null) return false;
        return v.x >= this.xx0 && v.x <= this.xx1 && v.y >= this.yy0 && v.y <= this.yy1;
    }
    
    public void wasExploded(final Level level, final int x, final int y, final int z) {
    }
    
    public int getRenderLayer() {
        return 0;
    }
    
    public boolean mayPlace(final Level level, final int x, final int y, final int z, final int face) {
        return this.mayPlace(level, x, y, z);
    }
    
    public boolean mayPlace(final Level level, final int x, final int y, final int z) {
        final int t = level.getTile(x, y, z);
        return t == 0 || Block.BLOCKS[t].material.isReplaceable();
    }
    
    public boolean use(final Level level, final int x, final int y, final int z, final Player player) {
        return false;
    }
    
    public void stepOn(final Level level, final int x, final int y, final int z, final Entity entity) {
    }
    
    public void setPlacedOnFace(final Level level, final int x, final int y, final int z, final int face) {
    }

    // Useless - Exists in b1.2 and LCE leaks
    public void prepareRender(Level level, int x, int y, int z) {
    }
    
    public void attack(final Level level, final int x, final int y, final int z, final Player player) {
    }
    
    public void handleEntityInside(final Level level, final int x, final int y, final int z, final Entity e, final Vec3 current) {
    }
    
    public void updateShape(final LevelSource level, final int x, final int y, final int z) {
    }
    
    public int getColor(final int auxData) {
        return 0xffffff;
    }
    
    public int getColor(final LevelSource level, final int x, final int y, final int z) {
        return 0xffffff;
    }
    
    public boolean getSignal(final LevelSource level, final int x, final int y, final int z, final int dir) {
        return false;
    }
    
    public boolean isSignalSource() {
        return false;
    }
    
    public void entityInside(final Level level, final int x, final int y, final int z, final Entity entity) {
    }
    
    public boolean getDirectSignal(final Level level, final int x, final int y, final int z, final int dir) {
        return false;
    }
    
    public void updateDefaultShape() {
    }
    
    public void playerDestroy(final Level level, final Player player, final int x, final int y, final int z, final int data) {
        player.awardStat(Stats.blockMined[this.id], 1);
        this.spawnResources(level, x, y, z, data);
    }
    
    public boolean canSurvive(final Level level, final int x, final int y, final int z) {
        return true;
    }
    
    public void setPlacedBy(final Level level, final int x, final int y, final int z, final Mob by) {
    }

    public String getName() {
        return I18n.get(this.getDescriptionId() + ".name");
    }

    @Override
    public Block setDescriptionId(final String id) {
        this.descriptionId = TILE_DESCRIPTION_PREFIX + id;
        return this;
    }

    @Override
    public String getDescriptionId() {
        return this.descriptionId;
    }
    
    public void triggerEvent(final Level level, final int x, final int y, final int z, final int b0, final int b1) {
    }
    
    public boolean isCollectStatistics() {
        return this.collectStatistics;
    }
    
    protected Block setNotCollectStatistics() {
        this.collectStatistics = false;
        return this;
    }
    
    public int getPistonPushReaction() {
        return this.material.getPushReaction();
    }
    
    static {
        Item.items[Block.cloth.id] = new ClothTileItem(Block.cloth.id - Block.TILE_NUM_COUNT).setDescriptionId("cloth");
        Item.items[Block.treeTrunk.id] = new TreeTileItem(Block.treeTrunk.id - Block.TILE_NUM_COUNT).setDescriptionId("log");
        Item.items[Block.stoneSlabHalf.id] = new StoneSlabTileItem(Block.stoneSlabHalf.id - Block.TILE_NUM_COUNT).setDescriptionId("stoneSlab");
        Item.items[Block.sapling.id] = new SaplingTileItem(Block.sapling.id - Block.TILE_NUM_COUNT).setDescriptionId("sapling");
        Item.items[Block.leaves.id] = new LeavesTileItem(Block.leaves.id - Block.TILE_NUM_COUNT).setDescriptionId("leaves");
        Item.items[Block.pistonBase.id] = new PistonTileItem(Block.pistonBase.id - Block.TILE_NUM_COUNT);
        Item.items[Block.pistonStickyBase.id] = new PistonTileItem(Block.pistonStickyBase.id - Block.TILE_NUM_COUNT);
        for (int i = 0; i < Block.TILE_NUM_COUNT; ++i) {
            if (Block.BLOCKS[i] != null && Item.items[i] == null) {
                Item.items[i] = new TileItem(i - Block.TILE_NUM_COUNT);
                Block.BLOCKS[i].init();
            }
        }
        Block.transculent[0] = true;
        Stats.buildBlockStats();
    }

    public static class SoundType
    {
        public final String name;
        public final float volume;
        public final float pitch;

        public SoundType(final String name, final float volume, final float pitch) {
            this.name = name;
            this.volume = volume;
            this.pitch = pitch;
        }

        public float getVolume() {
            return this.volume;
        }

        public float getPitch() {
            return this.pitch;
        }

        public String getBreakSound() {
            return "step." + this.name;
        }

        public String getStepSound() {
            return "step." + this.name;
        }
    }
}
