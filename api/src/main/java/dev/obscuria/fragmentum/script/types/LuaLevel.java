package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.level.Level;

@SuppressWarnings("all")
public class LuaLevel extends LuaWrapper<Level> {

    public static final LuaOps<Level> OPS = null;
    public static final LuaOps.Nilable<Level> NIL_OPS = null;

    public LuaLevel(Level value) {
        super(value, null);
    }

    @Override
    protected void build(Builder builder) {}
}
