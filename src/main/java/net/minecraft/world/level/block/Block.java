// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.level.block;

import net.minecraft.Facing;
import net.minecraft.locale.Descriptive;
import net.minecraft.locale.language.I18n;
import net.minecraft.world.entity.EntityMob;
import net.minecraft.stats.Stats;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.EntityItem;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.entity.player.EntityPlayer;
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
    
    public float getDestroyProgress(final EntityPlayer player) {
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
        final EntityItem item = new EntityItem(level, x + xo, y + yo, z + zo, itemInstance);
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
    
    public boolean use(final Level level, final int x, final int y, final int z, final EntityPlayer player) {
        return false;
    }
    
    public void stepOn(final Level level, final int x, final int y, final int z, final Entity entity) {
    }
    
    public void setPlacedOnFace(final Level level, final int x, final int y, final int z, final int face) {
    }

    // Useless - Exists in b1.2 and LCE leaks
    public void prepareRender(Level level, int x, int y, int z) {
    }
    
    public void attack(final Level level, final int x, final int y, final int z, final EntityPlayer player) {
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
    
    public void playerDestroy(final Level level, final EntityPlayer player, final int x, final int y, final int z, final int data) {
        player.awardStat(Stats.blockMined[this.id], 1);
        this.spawnResources(level, x, y, z, data);
    }
    
    public boolean canSurvive(final Level level, final int x, final int y, final int z) {
        return true;
    }
    
    public void setPlacedBy(final Level level, final int x, final int y, final int z, final EntityMob by) {
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
