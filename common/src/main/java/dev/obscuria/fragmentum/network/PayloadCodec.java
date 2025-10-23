package dev.obscuria.fragmentum.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import net.minecraft.Util;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.GsonHelper;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface PayloadCodec<T> {

    static <T> PayloadCodec<T> registryFriendly(Codec<T> codec, Supplier<RegistryAccess> registryAccess) {
        return new RegistryFriendly<>(codec, registryAccess);
    }

    void write(FriendlyByteBuf buf, T value);

    T read(FriendlyByteBuf buf);

    record RegistryFriendly<T>(Codec<T> codec, Supplier<RegistryAccess> registryAccess) implements PayloadCodec<T> {

        private static final int MAX_LENGTH = 1048576;
        private static final Gson GSON = new Gson();

        @Override
        public void write(FriendlyByteBuf buf, T value) {
            final var ops = RegistryOps.create(JsonOps.INSTANCE, registryAccess.get());
            buf.writeUtf(GSON.toJson(Util.getOrThrow(codec.encodeStart(ops, value), exception -> new EncoderException("Failed to encode: %s %s".formatted(exception, value)))), MAX_LENGTH);
        }

        @Override
        public T read(FriendlyByteBuf buf) {
            final var element = GsonHelper.fromJson(GSON, buf.readUtf(MAX_LENGTH), JsonElement.class);
            final var ops = RegistryOps.create(JsonOps.INSTANCE, registryAccess.get());
            return Util.getOrThrow(codec.parse(ops, element), exception -> new DecoderException("Failed to decode json: %s".formatted(exception)));
        }
    }
}
