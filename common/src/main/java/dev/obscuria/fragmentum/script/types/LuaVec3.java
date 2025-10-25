package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.phys.Vec3;

public final class LuaVec3 extends LuaWrapper<Vec3> {

    public static final LuaOps<Vec3> OPS;

    public LuaVec3(Vec3 value) {
        super(value);
    }

    @Override
    protected void build(Builder builder) {
        builder.put("x", Property.readOnly(LuaOps.DOUBLE, Vec3::x));
        builder.put("y", Property.readOnly(LuaOps.DOUBLE, Vec3::y));
        builder.put("z", Property.readOnly(LuaOps.DOUBLE, Vec3::z));
        builder.put("normalize", method0(LuaVec3.OPS, Vec3::normalize));
        builder.put("add", method3(LuaOps.DOUBLE, LuaOps.DOUBLE, LuaOps.DOUBLE, LuaVec3.OPS, Vec3::add));
    }

    static {
        OPS = new LuaOps.Instance<>(LuaVec3::new, value -> ((LuaVec3) value).getSource());
    }
}
