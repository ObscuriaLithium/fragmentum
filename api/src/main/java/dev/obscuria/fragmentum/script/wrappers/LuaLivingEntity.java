package dev.obscuria.fragmentum.script.wrappers;

import net.minecraft.world.entity.LivingEntity;

@SuppressWarnings("all")
public class LuaLivingEntity<T extends LivingEntity> extends LuaEntity<T> {

    public static final LuaOps<LivingEntity> OPS = null;

    public LuaLivingEntity(T entity) {
        super(entity);
    }

    @Override
    protected void build(LuaWrapper<T>.Builder builder) {}
}
