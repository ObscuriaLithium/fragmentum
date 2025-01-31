package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.api.v1.common.IPayloadRegistrar;
import dev.obscuria.fragmentum.neoforge.NeoFragmentum;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public record NeoPayloadRegistrar(String modId, AtomicBoolean optional) implements IPayloadRegistrar
{
    @Override
    public void allowClientOnly()
    {
        this.optional.set(true);
    }

    @Override
    public void allowServerOnly()
    {
        this.optional.set(true);
    }

    @Override
    public <T extends CustomPacketPayload> void registerClientbound(
            Class<T> clazz,
            CustomPacketPayload.Type<T> type,
            StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
            BiConsumer<Player, T> handler)
    {
        NeoFragmentum.eventBus(modId).addListener((RegisterPayloadHandlersEvent event) ->
        {
            var registrar = event.registrar("1");
            if (optional.get()) registrar = registrar.optional();
            registrar.playToClient(type, streamCodec, (payload, context) ->
                    context.enqueueWork(() ->
                    {
                        NeoV1Common.replyConsumer = context::reply;
                        handler.accept(context.player(), payload);
                        NeoV1Common.replyConsumer = null;
                    }));
        });
    }

    @Override
    public <T extends CustomPacketPayload> void registerServerbound(
            Class<T> clazz,
            CustomPacketPayload.Type<T> type,
            StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
            BiConsumer<ServerPlayer, T> handler)
    {
        NeoFragmentum.eventBus(modId).addListener((RegisterPayloadHandlersEvent event) ->
        {
            var registrar = event.registrar("1");
            if (optional.get()) registrar = registrar.optional();
            registrar.playToServer(type, streamCodec, (payload, context) ->
                    context.enqueueWork(() ->
                    {
                        NeoV1Common.replyConsumer = context::reply;
                        handler.accept((ServerPlayer) context.player(), payload);
                        NeoV1Common.replyConsumer = null;
                    }));
        });
    }
}
