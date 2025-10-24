package dev.obscuria.fragmentum.script;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScriptLoader implements ResourceManagerReloadListener {

    public static final ScriptLoader INSTANCE = new ScriptLoader();

    @Override
    public void onResourceManagerReload(ResourceManager manager) {

        ScriptRegistry.onReloadStart();
        final var files = manager.listResources("script", ScriptLoader::isValidScript);
        for (var entry : files.entrySet()) {
            final var location = entry.getKey();
            final var resource = entry.getValue();
            try (var stream = resource.open()) {
                final var code = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
                final var globals = JsePlatform.standardGlobals();
                final var script = new Script(globals, globals.load(code, location.toString()));
                script.init();
                ScriptRegistry.register(makeKey(location), script);
                System.out.println("Loaded Lua script " + script);
            } catch (Exception exception) {
                throw new RuntimeException("Failed to load Lua script " + location, exception);
            }
        }
        ScriptRegistry.onReloadEnd();
    }

    private static boolean isValidScript(ResourceLocation location) {
        return location.getPath().endsWith(".lua");
    }

    private static ResourceLocation makeKey(ResourceLocation location) {
        return location.withPath(path -> path
                .replaceFirst("script/", "")
                .replace(".lua", ""));
    }
}