package dev.obscuria.fragmentum.content.script;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.Globals;

@SuppressWarnings("all")
public final class ServerScriptLoader {

    public static @Nullable ScriptInstance load(@Nullable MinecraftServer server, ResourceLocation location) {
        throw new NotImplementedException();
    }

    public static @Nullable ScriptInstance load(@Nullable MinecraftServer server, ResourceLocation location, Globals globals) {
        throw new NotImplementedException();
    }
}
