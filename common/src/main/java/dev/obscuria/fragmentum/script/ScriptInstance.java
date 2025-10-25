package dev.obscuria.fragmentum.script;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

public record ScriptInstance(
        ResourceLocation location,
        Globals globals,
        LuaValue chunk
) {

    public @Nullable LuaValue function(String name) {
        final var value = globals.get(name);
        return value.isfunction() ? value : null;
    }
}
