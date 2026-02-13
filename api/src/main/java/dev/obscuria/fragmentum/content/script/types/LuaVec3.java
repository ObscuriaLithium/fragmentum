package dev.obscuria.fragmentum.content.script.types;

import net.minecraft.world.phys.Vec3;

@SuppressWarnings("all")
public final class LuaVec3 extends LuaWrapper<Vec3> {

    public static final LuaOps<Vec3> OPS = null;
    public static final LuaOps.Nilable<Vec3> NIL_OPS = null;

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
}
