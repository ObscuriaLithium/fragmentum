package dev.obscuria.fragmentum.script.types;

import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("all")
public class LuaResourceLocation extends LuaWrapper<ResourceLocation> {

    public static final LuaOps<ResourceLocation> OPS = null;

    public LuaResourceLocation(final ResourceLocation entity) {
        super(entity);
    }

    @Override
    protected void build(Builder builder) {}
}