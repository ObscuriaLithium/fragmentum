package dev.obscuria.fragmentum.script.types;

import net.minecraft.resources.ResourceLocation;

public class LuaResourceLocation extends LuaWrapper<ResourceLocation> {

    public static final LuaOps<ResourceLocation> OPS;

    public LuaResourceLocation(final ResourceLocation entity) {
        super(entity);
    }

    @Override
    protected void build(Builder builder) {}

    static {
        OPS = new LuaOps.Instance<>(LuaResourceLocation::new, value -> ((LuaResourceLocation) value).getSource());
    }
}