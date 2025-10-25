package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.damagesource.DamageSource;

@SuppressWarnings("all")
public class LuaDamageSource extends LuaWrapper<DamageSource> {

    public static final LuaOps<DamageSource> OPS = null;

    public LuaDamageSource(final DamageSource entity) {
        super(entity);
    }

    @Override
    protected void build(Builder builder) {}
}