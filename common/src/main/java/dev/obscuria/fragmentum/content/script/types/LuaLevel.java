package dev.obscuria.fragmentum.content.script.types;

import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaValue;

public class LuaLevel extends LuaWrapper<Level> {

    public static final LuaOps<Level> OPS;
    public static final LuaOps.Nilable<Level> NIL_OPS;

    public LuaLevel(Level value) {
        super(value, OPS);
    }

    @Override
    protected void build(Builder builder) {
        builder.put("isClientSide", method0(LuaOps.BOOL, Level::isClientSide));
        builder.put("isDay", method0(LuaOps.BOOL, Level::isDay));
    }

    private static LuaValue valueOf(@Nullable Level source) {
        return source == null ? LuaValue.NIL : new LuaLevel(source);
    }

    private static @Nullable Level nilableSourceOf(LuaValue value) {
        return value.isnil() ? null : ((LuaLevel) value).getSource();
    }

    private static Level nonnilSourceOf(LuaValue value) {
        return ((LuaLevel) value).getSource();
    }

    static {
        OPS = LuaOps.nonnil(LuaLevel::valueOf, LuaLevel::nonnilSourceOf);
        NIL_OPS = LuaOps.nilable(LuaLevel::valueOf, LuaLevel::nilableSourceOf);
    }
}
