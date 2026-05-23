package dev.obscuria.fragmentum;

import dev.obscuria.fragmentum.service.FragmentumServices;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

public final class Fragmentum {

    public static final String MOD_ID = "fragmentum";
    public static final String MOD_NAME = "FragmentumAPI";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final Platform PLATFORM = ServiceLoader.load(Platform.class).findFirst().orElseThrow();
    public static final FragmentumServices SERVICES = ServiceLoader.load(FragmentumServices.class).findFirst().orElseThrow();

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void init() {}
}
