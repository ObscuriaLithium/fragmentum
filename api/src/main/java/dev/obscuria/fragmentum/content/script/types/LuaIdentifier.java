package dev.obscuria.fragmentum.content.script.types;

import net.minecraft.resources.Identifier;

@SuppressWarnings("all")
public class LuaIdentifier extends LuaWrapper<Identifier> {

    public static final LuaOps<Identifier> OPS = null;
    public static final LuaOps.Nilable<Identifier> NIL_OPS = null;

    public LuaIdentifier(final Identifier identifier) {
        super(identifier, OPS);
    }

    @Override
    protected void build(Builder builder) {}
}