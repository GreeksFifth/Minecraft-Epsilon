// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.server;

import net.minecraft.network.packet.Packet;
import java.util.ArrayList;

import net.minecraft.world.entity.EntityPainting;
import net.minecraft.world.entity.item.EntityFallingBlock;
import net.minecraft.world.entity.item.EntityTNTPrimed;
import net.minecraft.world.entity.Creature;
import net.minecraft.world.entity.animal.EntitySquid;
import net.minecraft.world.entity.item.EntityBoat;
import net.minecraft.world.entity.item.EntityMinecart;
import net.minecraft.world.entity.item.EntityItem;
import net.minecraft.world.entity.projectile.EntityProjectileEgg;
import net.minecraft.world.entity.projectile.EntityProjectileSnowball;
import net.minecraft.world.entity.projectile.EntityProjectileFireballGhast;
import net.minecraft.world.entity.projectile.EntityProjectileArrow;
import net.minecraft.world.entity.projectile.EntityProjectileFishingHook;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import java.util.HashSet;
import util.IntHashMap;
import java.util.Set;

public class EntityTracker
{
    private Set<TrackedEntity> entities = new HashSet<>();
    private IntHashMap<TrackedEntity> entityMap = new IntHashMap<>();
    private MinecraftServer server;
    private int maxRange;
    private int dimension;
    
    public EntityTracker(final MinecraftServer server, final int dimension) {
        this.server = server;
        this.dimension = dimension;
        this.maxRange = server.players.getMaxRange();
    }
    
    public void addEntity(final Entity e) {
        if (e instanceof ServerPlayer) {
            this.addEntity(e, 32 * 16, 2);
            final ServerPlayer player = (ServerPlayer)e;
            for (final TrackedEntity te : this.entities) {
                if (te.e != player) {
                    te.updatePlayer(player);
                }
            }
        }
        else if (e instanceof EntityProjectileFishingHook) this.addEntity(e, 16 * 4, 5, true);
        else if (e instanceof EntityProjectileArrow) this.addEntity(e, 16 * 4, 20, false);
        else if (e instanceof EntityProjectileFireballGhast) this.addEntity(e, 16 * 4, 10, false);
        else if (e instanceof EntityProjectileSnowball) this.addEntity(e, 16 * 4, 10, true);
        else if (e instanceof EntityProjectileEgg) this.addEntity(e, 16 * 4, 10, true);
        else if (e instanceof EntityItem) this.addEntity(e, 16 * 4, 20, true);
        else if (e instanceof EntityMinecart) this.addEntity(e, 16 * 10, 5, true);
        else if (e instanceof EntityBoat) this.addEntity(e, 16 * 10, 5, true);
        else if (e instanceof EntitySquid) this.addEntity(e, 16 * 10, 3, true);
        else if (e instanceof Creature) this.addEntity(e, 16 * 10, 3);
        else if (e instanceof EntityTNTPrimed) this.addEntity(e, 16 * 10, 10, true);
        else if (e instanceof EntityFallingBlock) this.addEntity(e, 16 * 10, 20, true);
        else if (e instanceof EntityPainting) this.addEntity(e, 16 * 10, Integer.MAX_VALUE, false);
    }
    
    public void addEntity(final Entity e, final int range, final int updateInterval) {
        this.addEntity(e, range, updateInterval, false);
    }
    
    public void addEntity(final Entity e, int range, final int updateInterval, final boolean trackDeltas) {
        if (range > this.maxRange) range = this.maxRange;
        if (this.entityMap.containsKey(e.entityId)) throw new IllegalStateException("Entity is already tracked!");

        final TrackedEntity te = new TrackedEntity(e, range, updateInterval, trackDeltas);
        this.entities.add(te);
        this.entityMap.put(e.entityId, te);
        te.updatePlayers(this.server.getLevel(this.dimension).players);
    }
    
    public void removeEntity(final Entity e) {
        if (e instanceof ServerPlayer) {
            final ServerPlayer player = (ServerPlayer)e;
            for (TrackedEntity te : this.entities) {
                te.removePlayer(player);
            }
        }

        final TrackedEntity te = (TrackedEntity)this.entityMap.remove(e.entityId);
        if (te != null) {
            this.entities.remove(te);
            te.broadcastRemoved();
        }
    }
    
    public void tick() {
        final ArrayList<Entity> movedPlayers = new ArrayList<>();
        for (final TrackedEntity te : this.entities) {
            te.tick(this.server.getLevel(this.dimension).players);
            if (te.moved && te.e instanceof ServerPlayer) {
                movedPlayers.add(te.e);
            }
        }

        for (int i = 0; i < movedPlayers.size(); ++i) {
            final ServerPlayer player = (ServerPlayer)movedPlayers.get(i);
            for (final TrackedEntity te : this.entities) {
                if (te.e != player) {
                    te.updatePlayer(player);
                }
            }
        }
    }
    
    public void broadcast(final Entity e, final Packet packet) {
        final TrackedEntity te = this.entityMap.get(e.entityId);
        if (te != null) {
            te.broadcast(packet);
        }
    }
    
    public void broadcastAndSend(final Entity e, final Packet packet) {
        final TrackedEntity te = this.entityMap.get(e.entityId);
        if (te != null) {
            te.broadcastAndSend(packet);
        }
    }
    
    public void clear(final ServerPlayer serverPlayer) {
        for (TrackedEntity te : this.entities) {
            te.clear(serverPlayer);
        }
    }
}
