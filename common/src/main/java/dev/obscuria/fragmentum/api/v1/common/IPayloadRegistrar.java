package dev.obscuria.fragmentum.api.v1.common;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.function.BiConsumer;

public interface IPayloadRegistrar
{
    void allowClientOnly();

    void allowServerOnly();

    <T extends CustomPacketPayload> void
    registerClientbound(Class<T> clazz,
                        CustomPacketPayload.Type<T> type,
                        StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
                        BiConsumer<Player, T> handler);

    <T extends CustomPacketPayload> void
    registerServerbound(Class<T> clazz,
                        CustomPacketPayload.Type<T> type,
                        StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
                        BiConsumer<ServerPlayer, T> handler);
}