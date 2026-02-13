package dev.obscuria.fragmentum.content.script.types;

import net.minecraft.world.level.Level;

@SuppressWarnings("all")
public class LuaLevel extends LuaWrapper<Level> {

    public static final LuaOps<Level> OPS = null;
    public static final LuaOps.Nilable<Level> NIL_OPS = null;

    public LuaLevel(Level value) {
        super(value, OPS);
    }

    @Override
    protected void build(Builder builder) {
        builder.put("isClientSide", method0(LuaOps.BOOL, Level::isClientSide));
    }
}
