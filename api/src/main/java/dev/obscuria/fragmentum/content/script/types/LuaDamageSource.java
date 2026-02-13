package dev.obscuria.fragmentum.content.script.types;

import net.minecraft.world.damagesource.DamageSource;

@SuppressWarnings("all")
public class LuaDamageSource extends LuaWrapper<DamageSource> {

    public static final LuaOps<DamageSource> OPS = null;
    public static final LuaOps.Nilable<DamageSource> NIL_OPS = null;

    public LuaDamageSource(final DamageSource source) {
        super(source, OPS);
    }

    @Override
    protected void build(Builder builder) {
        builder.put("isDirect", method0(LuaOps.BOOL, DamageSource::isDirect));
        builder.put("getDirectEntity", nilMethod0(LuaEntity.NIL_OPS, DamageSource::getEntity));
        builder.put("getEntity", nilMethod0(LuaEntity.NIL_OPS, DamageSource::getEntity));
    }
}