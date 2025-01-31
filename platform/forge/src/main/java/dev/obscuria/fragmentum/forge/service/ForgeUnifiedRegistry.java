package dev.obscuria.fragmentum.forge.service;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.*;
import dev.obscuria.fragmentum.api.v1.common.UnifiedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@ApiStatus.Internal
public final class ForgeUnifiedRegistry<T> implements UnifiedRegistry<T>
{
    private final ResourceKey<? extends Registry<T>> key;
    private final Codec<Holder<T>> holderByNameCodec;
    private final Mutable<IForgeRegistry<T>> source;

    public ForgeUnifiedRegistry(ResourceKey<? extends Registry<T>> key)
    {
        this.key = key;
        this.holderByNameCodec = createHolderByNameCodec();
        this.source = new MutableObject<>();
    }

    public void bind(IForgeRegistry<T> registry)
    {
        this.source.setValue(registry);
    }

    @Override
    public ResourceKey<? extends Registry<T>> key()
    {
        return this.key;
    }

    @Override
    public ResourceLocation name()
    {
        return key().registry();
    }

    @Override
    public Codec<T> byNameCodec()
    {
        return Codec.of(
                new Encoder<>()
                {
                    @Override
                    public <R> DataResult<R> encode(T input, DynamicOps<R> ops, R prefix)
                    {
                        return source.getValue().getCodec().encode(input, ops, prefix);
                    }
                },
                new Decoder<>()
                {
                    @Override
                    public <R> DataResult<Pair<T, R>> decode(DynamicOps<R> ops, R input)
                    {
                        return source.getValue().getCodec().decode(ops, input);
                    }
                });
    }

    @Override
    public Codec<Holder<T>> holderByNameCodec()
    {
        return this.holderByNameCodec;
    }

    @Override
    public Set<ResourceLocation> keySet()
    {
        return source.getValue().getKeys();
    }

    @Override
    public Set<Map.Entry<ResourceKey<T>, T>> entrySet()
    {
        return source.getValue().getEntries();
    }

    @Override
    public boolean containsKey(ResourceLocation id)
    {
        return source.getValue().containsKey(id);
    }

    @Override
    public Optional<T> get(ResourceLocation id)
    {
        return Optional.ofNullable(source.getValue().getValue(id));
    }

    @Override
    public Optional<Holder.Reference<T>> getHolder(ResourceLocation id)
    {
        return source.getValue().getDelegate(id);
    }

    @Override
    public Optional<ResourceLocation> getKey(T value)
    {
        return Optional.ofNullable(source.getValue().getKey(value));
    }

    @Override
    public Optional<ResourceLocation> getKey(Holder<T> holder)
    {
        return holder.unwrap().right().flatMap(this::getKey);
    }

    private Codec<Holder<T>> createHolderByNameCodec()
    {
        return this.referenceHolderWithLifecycle().flatComapMap((reference) -> reference, this::safeCastToReference);
    }

    private Codec<Holder.Reference<T>> referenceHolderWithLifecycle()
    {
        return ResourceLocation.CODEC.comapFlatMap(id -> this.getHolder(id)
                        .map(DataResult::success)
                        .orElseGet(() -> DataResult.error(() -> "Unknown registry key in " + this.key() + ": " + id)),
                reference -> reference.key().location());
    }

    private DataResult<Holder.Reference<T>> safeCastToReference(Holder<T> value)
    {
        return value instanceof Holder.Reference<T> reference
                ? DataResult.success(reference)
                : DataResult.error(() -> "Unregistered holder in " + this.key() + ": " + value);
    }
}
