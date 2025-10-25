package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.level.Level;

public class LuaLevel extends LuaWrapper<Level> {

    public static final LuaOps<Level> OPS;

    public LuaLevel(Level value) {
        super(value);
    }

    @Override
    protected void build(Builder builder) {
        builder.put("isClientSide", method0(LuaOps.BOOL, Level::isClientSide));
        builder.put("isDay", method0(LuaOps.BOOL, Level::isDay));
    }

    static {
        OPS = new LuaOps.Instance<>(LuaLevel::new, value -> ((LuaLevel) value).getSource());
    }
}
