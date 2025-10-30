package dev.obscuria.fragmentum.script.types;

import net.minecraft.resources.ResourceKey;

@SuppressWarnings("all")
public class LuaResourceKey extends LuaWrapper<ResourceKey<?>> {

    public static final LuaOps<ResourceKey<?>> OPS = null;
    public static final LuaOps.Nilable<ResourceKey<?>> NIL_OPS = null;

    public LuaResourceKey(final ResourceKey<?> resourceKey) {
        super(resourceKey, null);
    }

    @Override
    protected void build(Builder builder) {}
}