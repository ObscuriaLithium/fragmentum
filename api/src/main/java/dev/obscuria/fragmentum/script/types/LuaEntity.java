package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.entity.Entity;

@SuppressWarnings("all")
public class LuaEntity<T extends Entity> extends LuaWrapper<T> {

    public static final LuaOps<Entity> OPS = null;

    public LuaEntity(final T entity) {
        super(entity);
    }

    @Override
    protected void build(Builder builder) {}
}