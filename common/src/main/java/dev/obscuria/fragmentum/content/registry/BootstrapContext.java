package dev.obscuria.fragmentum.content.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
@SuppressWarnings("unused")
public interface BootstrapContext<T> {

    void register(String name, Supplier<T> value);

    static <T> BootstrapContext<T> create(
            Registrar registrar,
            ResourceKey<Registry<T>> registryKey,
            Function<String, Identifier> idResolver) {
        return (name, value) -> registrar.register(registryKey, idResolver.apply(name), value);
    }
}
