package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.api.v1.common.IPayloadRegistrar;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.function.BiConsumer;

public record FabricPayloadRegistrar(String modId) implements IPayloadRegistrar
{
    @Override
    public void allowClientOnly() {}

    @Override
    public void allowServerOnly() {}

    @Override
    public <T extends CustomPacketPayload> void
    registerClientbound(Class<T> clazz,
                        CustomPacketPayload.Type<T> type,
                        StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
                        BiConsumer<Player, T> handler)
    {
        PayloadTypeRegistry.playS2C().register(type, streamCodec);
        ClientPlayNetworking.registerGlobalReceiver(type, (payload, context) -> {
            final var client = context.client();
            if (client == null) return;
            client.execute(() -> {
                FabricV1Common.replyPacketSender = context.responseSender();
                handler.accept(context.player(), payload);
                FabricV1Common.replyPacketSender = null;
            });
        });
    }

    @Override
    public <T extends CustomPacketPayload> void
    registerServerbound(Class<T> clazz, CustomPacketPayload.Type<T> type,
                        StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
                        BiConsumer<ServerPlayer, T> handler)
    {
        PayloadTypeRegistry.playC2S().register(type, streamCodec);
        ServerPlayNetworking.registerGlobalReceiver(type, (payload, context) -> {
            final var server = context.player().getServer();
            if (server == null) return;
            server.execute(() -> {
                FabricV1Common.replyPacketSender = context.responseSender();
                handler.accept(context.player(), payload);
                FabricV1Common.replyPacketSender = null;
            });
        });
    }
}
