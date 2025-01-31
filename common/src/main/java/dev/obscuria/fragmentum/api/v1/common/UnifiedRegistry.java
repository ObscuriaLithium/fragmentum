package dev.obscuria.fragmentum.api.v1.common;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * A proxy for accessing and interacting with a Minecraft {@link Registry}.
 *
 * @param <T> The type of the objects stored in the registry.
 */
@SuppressWarnings("unused")
public interface UnifiedRegistry<T>
{
    /**
     * Retrieves the {@link ResourceKey} identifying the registry.
     *
     * @return The {@link ResourceKey} for this registry.
     */
    ResourceKey<? extends Registry<T>> key();

    /**
     * Retrieves the {@link ResourceLocation}, or name, of the registry.
     *
     * @return The {@link ResourceLocation} representing the name of the registry.
     */
    ResourceLocation name();

    /**
     * Provides a {@link Codec} for serializing and deserializing objects in the registry by their names.
     *
     * @return A {@link Codec} for objects in the registry, identified by their names.
     */
    Codec<T> byNameCodec();

    /**
     * Provides a {@link Codec} for serializing and deserializing {@link Holder} objects in the registry by their names.
     *
     * @return A {@link Codec} for registry {@link Holder}s, identified by their names.
     */
    Codec<Holder<T>> holderByNameCodec();

    /**
     * Retrieves the set of all resource locations (keys) in the registry.
     *
     * @return A {@link Set} of {@link ResourceLocation} objects representing the keys of the registry's entries.
     */
    Set<ResourceLocation> keySet();

    /**
     * Retrieves the set of all entries in the registry, represented as key-value pairs.
     *
     * @return A {@link Set} of {@link Map.Entry} objects mapping {@link ResourceKey}s to their corresponding registry entries.
     */
    Set<Map.Entry<ResourceKey<T>, T>> entrySet();

    /**
     * Checks if a specific resource location exists in the registry.
     *
     * @param id The {@link ResourceLocation} to check.
     * @return {@code true} if the registry contains an entry with the specified key, {@code false} otherwise.
     */
    boolean containsKey(ResourceLocation id);

    /**
     * Retrieves the registry entry associated with a specific {@link ResourceLocation}, if it exists.
     *
     * @param id The {@link ResourceLocation} of the entry to retrieve.
     * @return An {@link Optional} containing the object if found, or {@link Optional#empty()} otherwise.
     */
    Optional<T> get(ResourceLocation id);

    /**
     * Retrieves the {@link Holder.Reference} of an entry associated with a specific {@link ResourceLocation}, if it exists.
     *
     * @param id The {@link ResourceLocation} of the entry to retrieve.
     * @return An {@link Optional} containing the {@link Holder.Reference} if found, or {@link Optional#empty()} otherwise.
     */
    Optional<Holder.Reference<T>> getHolder(ResourceLocation id);

    /**
     * Retrieves the resource location associated with a specific registry entry value, if it exists.
     *
     * @param value The registry entry to find the key for.
     * @return An {@link Optional} containing the {@link ResourceLocation} key if found, or {@link Optional#empty()} otherwise.
     */
    Optional<ResourceLocation> getKey(T value);

    /**
     * Retrieves the resource location associated with a specific {@link Holder}, if it exists.
     *
     * @param holder The {@link Holder} to find the key for.
     * @return An {@link Optional} containing the {@link ResourceLocation} key if found, or {@link Optional#empty()} otherwise.
     */
    Optional<ResourceLocation> getKey(Holder<T> holder);
}