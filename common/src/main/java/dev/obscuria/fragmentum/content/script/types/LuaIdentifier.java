package dev.obscuria.fragmentum.content.script.types;

import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaValue;

public class LuaIdentifier extends LuaWrapper<Identifier> {

    public static final LuaOps<Identifier> OPS;
    public static final LuaOps.Nilable<Identifier> NIL_OPS;

    public LuaIdentifier(final Identifier entity) {
        super(entity, OPS);
    }

    @Override
    protected void build(Builder builder) {}

    private static LuaValue valueOf(@Nullable Identifier source) {
        return source == null ? LuaValue.NIL : new LuaIdentifier(source);
    }

    private static @Nullable Identifier nilableSourceOf(LuaValue value) {
        return value.isnil() ? null : ((LuaIdentifier) value).getSource();
    }

    private static Identifier nonnilSourceOf(LuaValue value) {
        return ((LuaIdentifier) value).getSource();
    }

    static {
        OPS = LuaOps.nonnil(LuaIdentifier::valueOf, LuaIdentifier::nonnilSourceOf);
        NIL_OPS = LuaOps.nilable(LuaIdentifier::valueOf, LuaIdentifier::nilableSourceOf);
    }
}