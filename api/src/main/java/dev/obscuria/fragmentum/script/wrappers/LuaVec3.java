package dev.obscuria.fragmentum.script.wrappers;

import net.minecraft.world.phys.Vec3;

@SuppressWarnings("all")
public final class LuaVec3 extends LuaWrapper<Vec3> {

    public static final LuaOps<Vec3> OPS = null;

    public LuaVec3(Vec3 value) {
        super(value);
    }

    @Override
    protected void build(LuaWrapper<Vec3>.Builder builder) {}
}
