package dev.obscuria.fragmentum.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.function.BiConsumer;
import java.util.function.Function;

@SuppressWarnings("unused")
public interface PayloadRegistrar
{
    void allowClientOnly();

    void allowServerOnly();

    <T> void registerClientbound(Class<T> clazz, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<Player, T> handler);

    <T> void registerServerbound(Class<T> clazz, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<ServerPlayer, T> handler);
}
