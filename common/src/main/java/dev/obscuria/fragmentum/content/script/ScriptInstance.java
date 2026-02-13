package dev.obscuria.fragmentum.content.script;

import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

public record ScriptInstance(
        Identifier identifier,
        Globals globals,
        LuaValue chunk
) {

    public @Nullable LuaValue function(String name) {
        final var value = globals.get(name);
        return value.isfunction() ? value : null;
    }
}
