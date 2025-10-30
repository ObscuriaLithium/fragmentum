package dev.obscuria.fragmentum.script.types;

import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaValue;

public class LuaResourceKey extends LuaWrapper<ResourceKey<?>> {

    public static final LuaOps<ResourceKey<?>> OPS;
    public static final LuaOps.Nilable<ResourceKey<?>> NIL_OPS;

    public LuaResourceKey(final ResourceKey<?> resourceKey) {
        super(resourceKey, OPS);
    }

    @Override
    protected void build(Builder builder) {}

    private static LuaValue valueOf(@Nullable ResourceKey<?> source) {
        return source == null ? LuaValue.NIL : new LuaResourceKey(source);
    }

    private static @Nullable ResourceKey<?> nilableSourceOf(LuaValue value) {
        return value.isnil() ? null : ((LuaResourceKey) value).getSource();
    }

    private static ResourceKey<?> nonnilSourceOf(LuaValue value) {
        return ((LuaResourceKey) value).getSource();
    }

    static {
        OPS = LuaOps.nonnil(LuaResourceKey::valueOf, LuaResourceKey::nonnilSourceOf);
        NIL_OPS = LuaOps.nilable(LuaResourceKey::valueOf, LuaResourceKey::nilableSourceOf);
    }
}