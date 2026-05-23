package dev.obscuria.fragmentum.v2.api.common.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
@SuppressWarnings("unused")
public interface BootstrapContext<T> {

    static <T> BootstrapContext<T> create(
            Registrar registrar,
            ResourceKey<Registry<T>> registryKey,
            Function<String, Identifier> idResolver
    ) {
        throw new NotImplementedException();
    }

    void register(String name, Supplier<T> value);
}
