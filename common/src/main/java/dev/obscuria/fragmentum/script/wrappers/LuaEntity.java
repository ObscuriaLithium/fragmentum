package dev.obscuria.fragmentum.script.wrappers;

import net.minecraft.world.entity.Entity;

public class LuaEntity<T extends Entity> extends LuaWrapper<T> {

    public static final LuaOps<Entity> OPS;

    public LuaEntity(final T entity) {
        super(entity);
    }

    @Override
    protected void build(Builder builder) {
        builder.put("tickCount", Property.of(LuaOps.INT, LuaEntity::getTickCount, LuaEntity::setTickCount));
        builder.put("position", Property.of(LuaVec3.OPS, Entity::position, Entity::setPos));
        builder.put("level", Property.readOnly(LuaLevel.OPS, Entity::level));
        builder.put("canSeeSky", method0(LuaOps.BOOL, LuaEntity::canSeeSky));
        builder.put("isInWaterOrRain", method0(LuaOps.BOOL, Entity::isInWaterOrRain));
    }

    private static int getTickCount(Entity entity) {
        return entity.tickCount;
    }

    private static void setTickCount(Entity entity, int tickCount) {
        entity.tickCount = tickCount;
    }

    private static boolean canSeeSky(Entity entity) {
        return entity.level().canSeeSky(entity.blockPosition());
    }

    static {
        OPS = new LuaOps.Instance<>(LuaEntity::new, value -> ((LuaEntity<?>) value).getSource());
    }
}