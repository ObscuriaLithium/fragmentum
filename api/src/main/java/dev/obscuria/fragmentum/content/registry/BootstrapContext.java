package dev.obscuria.fragmentum.content.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface BootstrapContext<T> {

    void register(String name, Supplier<T> value);

    static <T> BootstrapContext<T> create(
            Registrar registrar,
            ResourceKey<Registry<T>> registryKey,
            Function<String, ResourceLocation> idResolver) {
        throw new NotImplementedException();
    }
}
