// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.client.renderer.entity;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import util.Mth;
import net.minecraft.world.entity.global.EntityLightningBolt;
import net.minecraft.world.entity.projectile.EntityProjectileFishingHook;
import net.minecraft.world.entity.item.EntityBoat;
import net.minecraft.world.entity.item.EntityMinecart;
import net.minecraft.world.entity.item.EntityFallingBlock;
import net.minecraft.world.entity.item.EntityTNTPrimed;
import net.minecraft.world.entity.item.EntityItem;
import net.minecraft.world.entity.projectile.EntityProjectileFireballGhast;
import net.minecraft.world.entity.projectile.EntityProjectileEgg;
import net.minecraft.world.entity.projectile.EntityProjectileSnowball;
import net.minecraft.world.entity.projectile.EntityProjectileArrow;
import net.minecraft.world.entity.EntityPainting;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.world.entity.animal.EntitySquid;
import net.minecraft.world.entity.monster.EntityGhast;
import net.minecraft.world.entity.monster.EntityGiant;
import net.minecraft.world.entity.player.EntityPlayer;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.world.entity.monster.EntitySlime;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.world.entity.monster.EntityZombie;
import net.minecraft.client.model.ModelHumanoid;
import net.minecraft.client.model.ModelSkeletonArcher;
import net.minecraft.world.entity.monster.EntitySkeletonArcher;
import net.minecraft.world.entity.monster.EntityCreeper;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.world.entity.animal.EntityChicken;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.world.entity.animal.EntityWolf;
import net.minecraft.client.model.ModelCow;
import net.minecraft.world.entity.animal.EntityCow;
import net.minecraft.client.model.ModelSheepFur;
import net.minecraft.client.model.ModelSheep;
import net.minecraft.world.entity.animal.EntitySheep;
import net.minecraft.client.model.ModelPig;
import net.minecraft.world.entity.animal.EntityPig;
import net.minecraft.world.entity.monster.EntitySpider;
import java.util.HashMap;
import net.minecraft.client.Options;
import net.minecraft.world.entity.EntityMob;
import net.minecraft.world.level.Level;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.Textures;
import net.minecraft.client.gui.Font;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

public class EntityRenderDispatcher
{
    private Map<Class<? extends Entity>, EntityRenderer<? extends Entity>> renderers = new HashMap<>();
    public static EntityRenderDispatcher instance = new EntityRenderDispatcher();
    private Font font;
    public static double xOff;
    public static double yOff;
    public static double zOff;
    public Textures textures;
    public ItemInHandRenderer itemInHandRenderer;
    public Level level;
    public EntityMob player;
    public float playerRotY;
    public float playerRotX;
    public Options options;
    public double xPlayer;
    public double yPlayer;
    public double zPlayer;
    
    private EntityRenderDispatcher() {
        this.renderers.put(EntitySpider.class, new SpiderRenderer());
        this.renderers.put(EntityPig.class, new PigRenderer(new ModelPig(), new ModelPig(0.5f), 0.7f));
        this.renderers.put(EntitySheep.class, new SheepRenderer(new ModelSheep(), new ModelSheepFur(), 0.7f));
        this.renderers.put(EntityCow.class, new CowRenderer(new ModelCow(), 0.7f));
        this.renderers.put(EntityWolf.class, new WolfRenderer(new ModelWolf(), 0.5f));
        this.renderers.put(EntityChicken.class, new ChickenRenderer(new ModelChicken(), 0.3f));
        this.renderers.put(EntityCreeper.class, new CreeperRenderer());
        this.renderers.put(EntitySkeletonArcher.class, new HumanoidMobRenderer<>(new ModelSkeletonArcher(), 0.5f));
        this.renderers.put(EntityZombie.class, new HumanoidMobRenderer<>(new ModelZombie(), 0.5f));
        this.renderers.put(EntitySlime.class, new SlimeRenderer(new ModelSlime(16), new ModelSlime(0), 0.25f));
        this.renderers.put(EntityPlayer.class, new PlayerRenderer());
        this.renderers.put(EntityGiant.class, new GiantMobRenderer(new ModelZombie(), 0.5f, 6.0f));
        this.renderers.put(EntityGhast.class, new GhastRenderer());
        this.renderers.put(EntitySquid.class, new SquidRenderer(new ModelSquid(), 0.7f));
        this.renderers.put(EntityMob.class, new MobRenderer<>(new ModelHumanoid(), 0.5f));
        this.renderers.put(Entity.class, new DefaultRenderer());
        this.renderers.put(EntityPainting.class, new PaintingRenderer());
        this.renderers.put(EntityProjectileArrow.class, new ArrowRenderer());
        this.renderers.put(EntityProjectileSnowball.class, new ItemSpriteRenderer(Items.snowBall.getIcon(0)));
        this.renderers.put(EntityProjectileEgg.class, new ItemSpriteRenderer(Items.egg.getIcon(0)));
        this.renderers.put(EntityProjectileFireballGhast.class, new FireballRenderer());
        this.renderers.put(EntityItem.class, new ItemRenderer());
        this.renderers.put(EntityTNTPrimed.class, new TntRenderer());
        this.renderers.put(EntityFallingBlock.class, new FallingBlockRenderer());
        this.renderers.put(EntityMinecart.class, new MinecartRenderer());
        this.renderers.put(EntityBoat.class, new BoatRenderer());
        this.renderers.put(EntityProjectileFishingHook.class, new FishingHookRenderer());
        this.renderers.put(EntityLightningBolt.class, new LightningBoltRenderer());

        for (EntityRenderer<? extends Entity> entityRenderer : this.renderers.values()) {
            entityRenderer.init(this);
        }
    }
    
    public <T extends Entity> EntityRenderer<T> getRenderer(final Class<? extends Entity> clazz) {
        EntityRenderer<? extends Entity> r = this.renderers.get(clazz);
        if (r == null && clazz != Entity.class) {
            r = this.getRenderer((Class<? extends T>) clazz.getSuperclass());
            this.renderers.put(clazz, r);
        }
        return (EntityRenderer<T>) r;
    }
    
    public <T extends Entity> EntityRenderer<T> getRenderer(final Entity entity) {
        return this.getRenderer(entity.getClass());
    }
    
    public void prepare(final Level level, final Textures textures, final Font font, final EntityMob player, final Options options, final float a) {
        this.level = level;
        this.textures = textures;
        this.options = options;
        this.player = player;
        this.font = font;

        if (player.isSleeping()) {
            int t = level.getTile(Mth.floor(player.x), Mth.floor(player.y), Mth.floor(player.z));
            if (t == Blocks.bed.id) {
                int data = level.getData(Mth.floor(player.x), Mth.floor(player.y), Mth.floor(player.z));

                int direction = data & 0x3;
                this.playerRotY = (float)(direction * 90 + 180);
                this.playerRotX = 0.0f;
            }
        }
        else {
            this.playerRotY = player.yRotO + (player.yRot - player.yRotO) * a;
            this.playerRotX = player.xRotO + (player.xRot - player.xRotO) * a;
        }

        this.xPlayer = player.xOld + (player.x - player.xOld) * a;
        this.yPlayer = player.yOld + (player.y - player.yOld) * a;
        this.zPlayer = player.zOld + (player.z - player.zOld) * a;
    }
    
    public void render(final Entity entity, final float a) {
        final double x = entity.xOld + (entity.x - entity.xOld) * a;
        final double y = entity.yOld + (entity.y - entity.yOld) * a;
        final double z = entity.zOld + (entity.z - entity.zOld) * a;

        final float r = entity.yRotO + (entity.yRot - entity.yRotO) * a;
        final float br = entity.getBrightness(a);
        glColor3f(br, br, br);

        this.render(entity, x - EntityRenderDispatcher.xOff, y - EntityRenderDispatcher.yOff, z - EntityRenderDispatcher.zOff, r, a);
    }
    
    public void render(final Entity entity, final double x, final double y, final double z, final float rot, final float a) {
        final EntityRenderer<Entity> renderer = this.getRenderer(entity);
        if (renderer != null) {
            renderer.render(entity, x, y, z, rot, a);
            renderer.postRender(entity, x, y, z, rot, a);
        }
    }
    
    public void setLevel(final Level level) {
        this.level = level;
    }
    
    public double distanceToSqr(final double x, final double y, final double z) {
        final double xd = x - this.xPlayer;
        final double yd = y - this.yPlayer;
        final double zd = z - this.zPlayer;
        return xd * xd + yd * yd + zd * zd;
    }
    
    public Font getFont() {
        return this.font;
    }

}
