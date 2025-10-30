package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaValue;

public class LuaEntity<T extends Entity> extends LuaWrapper<T> {

    public static final LuaOps<Entity> OPS;
    public static final LuaOps.Nilable<Entity> NIL_OPS;

    public LuaEntity(final T entity) {
        super(entity, LuaOps.nonnil(LuaEntity::valueOf, LuaEntity::nonnilSourceOf));
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
        return entity.tickCount;
    }

    private static void setTickCount(Entity entity, int tickCount) {
        entity.tickCount = tickCount;
    }

    private static boolean canSeeSky(Entity entity) {
        return entity.level().canSeeSky(entity.blockPosition());
    }

    private static <T extends Entity> LuaValue valueOf(@Nullable T entity) {
        return entity == null ? LuaValue.NIL : new LuaEntity<>(entity);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> @Nullable T nilableSourceOf(LuaValue value) {
        return value.isnil() ? null : ((LuaEntity<T>) value).getSource();
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> T nonnilSourceOf(LuaValue value) {
        return ((LuaEntity<T>) value).getSource();
    }

    static {
        OPS = LuaOps.nonnil(LuaEntity::valueOf, LuaEntity::nonnilSourceOf);
        NIL_OPS = LuaOps.nilable(LuaEntity::valueOf, LuaEntity::nilableSourceOf);
    }
}