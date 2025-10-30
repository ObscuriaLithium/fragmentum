package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.entity.Entity;

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
    protected void build(Builder builder) {}
}