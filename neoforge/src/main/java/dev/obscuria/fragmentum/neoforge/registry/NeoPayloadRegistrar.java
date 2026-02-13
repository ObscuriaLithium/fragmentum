package dev.obscuria.fragmentum.neoforge.registry;

import dev.obscuria.fragmentum.content.network.PayloadRegistrar;
import dev.obscuria.fragmentum.neoforge.NeoFragmentum;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

import java.util.function.BiConsumer;

public record NeoPayloadRegistrar(String modId) implements PayloadRegistrar {

    @Override
    public void allowClientOnly() {
        NeoNetworking.clientOnlyMods.add(modId);
    }

    @Override
    public void allowServerOnly() {
        NeoNetworking.serverOnlyMods.add(modId);
    }

    @Override
    public <T extends CustomPacketPayload> void registerClientbound(
            Class<T> clazz,
            CustomPacketPayload.Type<T> type,
            StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec,
            BiConsumer<Player, T> handler) {

        NeoFragmentum.addListener(modId, (final RegisterPayloadHandlersEvent event) -> {
            final var registrar = event.registrar("1");
            if (NeoNetworking.clientOnlyMods.contains(modId)) registrar.optional();
            registrar.playToClient(type, streamCodec, (payload, context) -> context.enqueueWork(() -> {
                NeoNetworking.replyConsumer = context::reply;
                handler.accept(context.player(), payload);
                NeoNetworking.replyConsumer = null;
            }));
        });
    }

    @Override
    public <T extends CustomPacketPayload> void registerServerbound(
            Class<T> clazz,
            CustomPacketPayload.Type<T> type,
            StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec,
            BiConsumer<ServerPlayer, T> handler) {

        NeoFragmentum.addListener(modId, (final RegisterPayloadHandlersEvent event) -> {
            final var registrar = event.registrar("1");
            if (NeoNetworking.serverOnlyMods.contains(modId)) registrar.optional();
            registrar.playToServer(type, streamCodec, (payload, context) -> context.enqueueWork(() -> {
                NeoNetworking.replyConsumer = context::reply;
                handler.accept((ServerPlayer) context.player(), payload);
                NeoNetworking.replyConsumer = null;
            }));
        });
    }
}
