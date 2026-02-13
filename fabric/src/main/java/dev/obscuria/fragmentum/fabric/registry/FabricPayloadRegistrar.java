package dev.obscuria.fragmentum.fabric.registry;

import dev.obscuria.fragmentum.Fragmentum;
import dev.obscuria.fragmentum.content.network.PayloadRegistrar;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.function.BiConsumer;

public record FabricPayloadRegistrar(String modId) implements PayloadRegistrar {

    @Override
    public void allowClientOnly() {}

    @Override
    public void allowServerOnly() {}

    @Override
    public <T extends CustomPacketPayload> void registerClientbound(
            Class<T> clazz,
            CustomPacketPayload.Type<T> type,
            StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec,
            BiConsumer<Player, T> handler) {

        PayloadTypeRegistry.playS2C().register(type, streamCodec);
        if (Fragmentum.PLATFORM.isDedicatedServer()) return;
        ClientPlayNetworking.registerGlobalReceiver(type, (payload, context) -> {
            FabricNetworking.clientReplySender = context.responseSender();
            handler.accept(context.player(), payload);
            FabricNetworking.clientReplySender = null;
        });
    }

    @Override
    public <T extends CustomPacketPayload> void registerServerbound(
            Class<T> clazz,
            CustomPacketPayload.Type<T> type,
            StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec,
            BiConsumer<ServerPlayer, T> handler) {

        PayloadTypeRegistry.playC2S().register(type, streamCodec);
        ServerPlayNetworking.registerGlobalReceiver(type, (payload, context) -> {
            FabricNetworking.serverReplySender = context.responseSender();
            handler.accept(context.player(), payload);
            FabricNetworking.serverReplySender = null;
        });
    }
}
