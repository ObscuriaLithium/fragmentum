package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.damagesource.DamageSource;

@SuppressWarnings("all")
public class LuaDamageSource extends LuaWrapper<DamageSource> {

    public static final LuaOps<DamageSource> OPS = null;
    public static final LuaOps.Nilable<DamageSource> NIL_OPS = null;

    public LuaDamageSource(final DamageSource entity) {
        super(entity, null);
    }

    @Override
    protected void build(Builder builder) {}
}