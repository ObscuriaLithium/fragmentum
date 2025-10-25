package dev.obscuria.fragmentum.script.types;

import net.minecraft.resources.ResourceKey;

public class LuaResourceKey extends LuaWrapper<ResourceKey<?>> {

    public static final LuaOps<ResourceKey<?>> OPS;

    public LuaResourceKey(final ResourceKey<?> resourceKey) {
        super(resourceKey);
    }

    @Override
    protected void build(Builder builder) {}

    static {
        OPS = new LuaOps.Instance<>(LuaResourceKey::new, value -> ((LuaResourceKey) value).getSource());
    }
}