package dev.obscuria.fragmentum.neoforge.registry;

import com.mojang.serialization.*;
import dev.obscuria.fragmentum.content.registry.DelegatedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("all")
public final class NeoDelegatedRegistry<T> implements DelegatedRegistry<T> {

    private final ResourceKey<? extends Registry<T>> key;
    private final Codec<Holder<T>> holderByNameCodec;
    private final Registry<T> source;

    public NeoDelegatedRegistry(Registry<T> registry) {
        this.holderByNameCodec = createHolderByNameCodec();
        this.source = registry;
        this.key = registry.key();
    }

    @Override
    public ResourceKey<? extends Registry<T>> key() {
        return this.key;
    }

    @Override
    public ResourceLocation name() {
        return key().registry();
    }

    @Override
    public Codec<T> byNameCodec() {
        return source.byNameCodec();
    }

    @Override
    public Codec<Holder<T>> holderByNameCodec() {
        return this.holderByNameCodec;
    }

    @Override
    public Set<ResourceLocation> keySet() {
        return source.keySet();
    }

    @Override
    public Set<Map.Entry<ResourceKey<T>, T>> entrySet() {
        return source.entrySet();
    }

    @Override
    public boolean containsKey(ResourceLocation id) {
        return source.containsKey(id);
    }

    @Override
    public T get(ResourceLocation id) {
        return source.get(id);
    }

    @Override
    public Holder.Reference<T> getHolder(ResourceLocation id) {
        return source.getHolder(id).orElse(null);
    }

    @Override
    public ResourceLocation getKey(T value) {
        return source.getKey(value);
    }

    @Override
    public ResourceLocation getKey(Holder<T> holder) {
        return holder.unwrap().right().map(this::getKey).orElse(null);
    }

    private Codec<Holder<T>> createHolderByNameCodec() {
        return this.referenceHolderWithLifecycle().flatComapMap((reference) -> reference, this::safeCastToReference);
    }

    private Codec<Holder.Reference<T>> referenceHolderWithLifecycle() {
        return ResourceLocation.CODEC.comapFlatMap(id -> Optional.ofNullable(this.getHolder(id))
                        .map(DataResult::success)
                        .orElseGet(() -> DataResult.error(() -> "Unknown registry key in " + this.key() + ": " + id)),
                reference -> reference.key().location());
    }

    private DataResult<Holder.Reference<T>> safeCastToReference(Holder<T> value) {
        return value instanceof Holder.Reference<T> reference
                ? DataResult.success(reference)
                : DataResult.error(() -> "Unregistered holder in " + this.key() + ": " + value);
    }
}
