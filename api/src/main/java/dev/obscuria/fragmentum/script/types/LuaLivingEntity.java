package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.entity.LivingEntity;

@SuppressWarnings("all")
public class LuaLivingEntity<T extends LivingEntity> extends LuaEntity<T> {

    public static final LuaOps<LivingEntity> OPS = null;
    public static final LuaOps.Nilable<LivingEntity> NIL_OPS = null;

    public LuaLivingEntity(T entity) {
        super(entity, null);
    }

    @Override
    protected void build(LuaWrapper<T>.Builder builder) {}
}
