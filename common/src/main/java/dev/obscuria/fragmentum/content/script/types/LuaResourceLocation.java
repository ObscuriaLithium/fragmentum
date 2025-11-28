package dev.obscuria.fragmentum.content.script.types;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaValue;

public class LuaResourceLocation extends LuaWrapper<ResourceLocation> {

    public static final LuaOps<ResourceLocation> OPS;
    public static final LuaOps.Nilable<ResourceLocation> NIL_OPS;

    public LuaResourceLocation(final ResourceLocation entity) {
        super(entity, OPS);
    }

    @Override
    protected void build(Builder builder) {}

    private static LuaValue valueOf(@Nullable ResourceLocation source) {
        return source == null ? LuaValue.NIL : new LuaResourceLocation(source);
    }

    private static @Nullable ResourceLocation nilableSourceOf(LuaValue value) {
        return value.isnil() ? null : ((LuaResourceLocation) value).getSource();
    }

    private static ResourceLocation nonnilSourceOf(LuaValue value) {
        return ((LuaResourceLocation) value).getSource();
    }

    static {
        OPS = LuaOps.nonnil(LuaResourceLocation::valueOf, LuaResourceLocation::nonnilSourceOf);
        NIL_OPS = LuaOps.nilable(LuaResourceLocation::valueOf, LuaResourceLocation::nilableSourceOf);
    }
}