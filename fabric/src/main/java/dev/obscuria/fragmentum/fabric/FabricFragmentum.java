package dev.obscuria.fragmentum.fabric;

import dev.obscuria.fragmentum.Fragmentum;
import dev.obscuria.fragmentum.fabric.script.FabricScriptLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;

public class FabricFragmentum implements ModInitializer {

    @Override
    public void onInitialize() {
        Fragmentum.init();
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(FabricScriptLoader.INSTANCE);
    }
}
