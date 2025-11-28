package dev.obscuria.fragmentum.content.script;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.nio.charset.StandardCharsets;

public final class ServerScriptLoader {

    public static @Nullable ScriptInstance load(@Nullable MinecraftServer server, ResourceLocation location) {
        return load(server, location, JsePlatform.standardGlobals());
    }

    public static @Nullable ScriptInstance load(@Nullable MinecraftServer server, ResourceLocation location, Globals globals) {
        if (server == null) return null;
        final @Nullable var resource = server.getResourceManager().getResource(location).orElse(null);
        if (resource == null) return null;
        try (var stream = resource.open()) {
            final var code = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
            final var chunk = globals.load(code, location.toString());
            chunk.call();
            return new ScriptInstance(location, globals, chunk);
        } catch (Exception exception) {
            Fragmentum.LOGGER.error("Failed to load Lua script: {}", location, exception);
            return null;
        }
    }
}
