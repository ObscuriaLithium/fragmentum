package dev.obscuria.fragmentum.fabric.script;

import dev.obscuria.fragmentum.Fragmentum;
import dev.obscuria.fragmentum.script.ScriptLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FabricScriptLoader implements SimpleSynchronousResourceReloadListener {

    public static final FabricScriptLoader INSTANCE = new FabricScriptLoader();
    private static final ResourceLocation ID = new ResourceLocation(Fragmentum.MOD_ID, "script");

    @Override
    public ResourceLocation getFabricId() {
        return ID;
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        ScriptLoader.INSTANCE.onResourceManagerReload(manager);
    }
}
