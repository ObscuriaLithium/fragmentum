package dev.obscuria.fragmentum.content.script.types;

import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("all")
public class LuaResourceLocation extends LuaWrapper<ResourceLocation> {

    public static final LuaOps<ResourceLocation> OPS = null;
    public static final LuaOps.Nilable<ResourceLocation> NIL_OPS = null;

    public LuaResourceLocation(final ResourceLocation entity) {
        super(entity, OPS);
    }

    @Override
    protected void build(Builder builder) {}
}