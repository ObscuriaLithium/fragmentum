package dev.obscuria.fragmentum.network;

import com.mojang.serialization.Codec;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface PayloadCodec<T> {

    static <T> PayloadCodec<T> registryFriendly(Codec<T> codec, Supplier<RegistryAccess> registryAccess) {
        throw new NotImplementedException();
    }

    void write(FriendlyByteBuf buf, T value);

    T read(FriendlyByteBuf buf);
}
