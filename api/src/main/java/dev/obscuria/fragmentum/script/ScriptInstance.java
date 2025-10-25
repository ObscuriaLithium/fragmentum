package dev.obscuria.fragmentum.script;

import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

public record ScriptInstance(
        ResourceLocation location,
        Globals globals,
        LuaValue chunk
) {

    public @Nullable LuaValue function(String name) {
        throw new NotImplementedException();
    }
}
