package dev.obscuria.fragmentum.script.wrappers;

import net.minecraft.world.level.Level;

@SuppressWarnings("all")
public class LuaLevel extends LuaWrapper<Level> {

    public static final LuaOps<Level> OPS = null;

    public LuaLevel(Level value) {
        super(value);
    }

    @Override
    protected void build(Builder builder) {}
}
