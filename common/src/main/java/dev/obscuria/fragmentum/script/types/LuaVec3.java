package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaValue;

public final class LuaVec3 extends LuaWrapper<Vec3> {

    public static final LuaOps<Vec3> OPS;
    public static final LuaOps.Nilable<Vec3> NIL_OPS;

    public LuaVec3(Vec3 value) {
        super(value, OPS);
    }

    @Override
    protected void build(Builder builder) {
        builder.put("x", Property.readOnly(LuaOps.DOUBLE, Vec3::x));
        builder.put("y", Property.readOnly(LuaOps.DOUBLE, Vec3::y));
        builder.put("z", Property.readOnly(LuaOps.DOUBLE, Vec3::z));
        builder.put("normalize", method0(LuaVec3.OPS, Vec3::normalize));
        builder.put("add", method3(LuaOps.DOUBLE, LuaOps.DOUBLE, LuaOps.DOUBLE, LuaVec3.OPS, Vec3::add));
    }

    private static LuaValue valueOf(@Nullable Vec3 source) {
        return source == null ? LuaValue.NIL : new LuaVec3(source);
    }

    private static @Nullable Vec3 nilableSourceOf(LuaValue value) {
        return value.isnil() ? null : ((LuaVec3) value).getSource();
    }

    private static Vec3 nonnilSourceOf(LuaValue value) {
        return ((LuaVec3) value).getSource();
    }

    static {
        OPS = LuaOps.nonnil(LuaVec3::valueOf, LuaVec3::nonnilSourceOf);
        NIL_OPS = LuaOps.nilable(LuaVec3::valueOf, LuaVec3::nilableSourceOf);
    }
}
