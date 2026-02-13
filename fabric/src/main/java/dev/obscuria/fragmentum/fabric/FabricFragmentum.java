package dev.obscuria.fragmentum.fabric;

import dev.obscuria.fragmentum.Fragmentum;
import net.fabricmc.api.ModInitializer;

public class FabricFragmentum implements ModInitializer {

    @Override
    public void onInitialize() {
        Fragmentum.init();
    }
}
