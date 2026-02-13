package dev.obscuria.fragmentum;

import dev.obscuria.fragmentum.client.FragmentumClient;
import dev.obscuria.fragmentum.service.FragmentumServices;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

@SuppressWarnings("unused")
public interface Fragmentum {

    String MODID = "fragmentum";
    String DISPLAY_NAME = "Fragmentum";
    Logger LOGGER = LoggerFactory.getLogger(DISPLAY_NAME);
    Platform PLATFORM = ServiceLoader.load(Platform.class).findFirst().orElseThrow();
    FragmentumServices SERVICES = ServiceLoader.load(FragmentumServices.class).findFirst().orElseThrow();

    static Identifier key(String name) {
        return Identifier.fromNamespaceAndPath(MODID, name);
    }

    static void init() {
        if (PLATFORM.isClient()) FragmentumClient.init();
    }
}