package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.damagesource.DamageSource;

public class LuaDamageSource extends LuaWrapper<DamageSource> {

    public static final LuaOps<DamageSource> OPS;

    public LuaDamageSource(final DamageSource entity) {
        super(entity);
    }

    @Override
    protected void build(Builder builder) {}

    static {
        OPS = new LuaOps.Instance<>(LuaDamageSource::new, value -> ((LuaDamageSource) value).getSource());
    }
}