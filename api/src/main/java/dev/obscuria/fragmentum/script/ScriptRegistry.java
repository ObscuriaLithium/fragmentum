package dev.obscuria.fragmentum.script;

import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("all")
public final class ScriptRegistry {

    public static void register(ResourceLocation id, Script script) {
        throw new NotImplementedException();
    }

    public static @Nullable ScriptHolder get(ResourceLocation key) {
        throw new NotImplementedException();
    }

    public static ScriptHolder getOrThrow(ResourceLocation key) {
        throw new NotImplementedException();
    }
}
