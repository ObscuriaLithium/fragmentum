package dev.obscuria.fragmentum.content.script.types;

import net.minecraft.world.entity.Entity;
import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("all")
public class LuaEntity<T extends Entity> extends LuaWrapper<T> {

    public static final LuaOps<Entity> OPS = null;
    public static final LuaOps.Nilable<Entity> NIL_OPS = null;

    public LuaEntity(final T entity) {
        super(entity, null);
    }

    public LuaEntity(final T entity, LuaOps<T> ops) {
        super(entity, ops);
    }

    @Override
    protected void build(Builder builder) {
        builder.put("tickCount", Property.of(LuaOps.INT, LuaEntity::getTickCount, LuaEntity::setTickCount));
        builder.put("position", Property.of(LuaVec3.OPS, Entity::position, Entity::setPos));
        builder.put("level", Property.readOnly(LuaLevel.OPS, Entity::level));
        builder.put("canSeeSky", method0(LuaOps.BOOL, LuaEntity::canSeeSky));
        builder.put("isInWaterOrRain", method0(LuaOps.BOOL, Entity::isInWaterOrRain));
        builder.put("isInWaterRainOrBubble", method0(LuaOps.BOOL, Entity::isInWaterRainOrBubble));
    }

    private static int getTickCount(Entity entity) {
        throw new NotImplementedException();
    }

    private static void setTickCount(Entity entity, int tickCount) {
        throw new NotImplementedException();
    }

    private static boolean canSeeSky(Entity entity) {
        throw new NotImplementedException();
    }
}