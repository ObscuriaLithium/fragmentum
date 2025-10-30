package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.damagesource.DamageSource;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaValue;

public class LuaDamageSource extends LuaWrapper<DamageSource> {

    public static final LuaOps<DamageSource> OPS;
    public static final LuaOps.Nilable<DamageSource> NIL_OPS;

    public LuaDamageSource(final DamageSource source) {
        super(source, OPS);
    }

    @Override
    protected void build(Builder builder) {
        builder.put("isIndirect", method0(LuaOps.BOOL, DamageSource::isIndirect));
        builder.put("getDirectEntity", nilMethod0(LuaEntity.NIL_OPS, DamageSource::getEntity));
        builder.put("getEntity", nilMethod0(LuaEntity.NIL_OPS, DamageSource::getEntity));
    }

    private static LuaValue valueOf(@Nullable DamageSource source) {
        return source == null ? LuaValue.NIL : new LuaDamageSource(source);
    }

    private static @Nullable DamageSource nilableSourceOf(LuaValue value) {
        return value.isnil() ? null : ((LuaDamageSource) value).getSource();
    }

    private static DamageSource nonnilSourceOf(LuaValue value) {
        return ((LuaDamageSource) value).getSource();
    }

    static {
        OPS = LuaOps.nonnil(LuaDamageSource::valueOf, LuaDamageSource::nonnilSourceOf);
        NIL_OPS = LuaOps.nilable(LuaDamageSource::valueOf, LuaDamageSource::nilableSourceOf);
    }
}