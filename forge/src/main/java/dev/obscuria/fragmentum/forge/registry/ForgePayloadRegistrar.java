package dev.obscuria.fragmentum.forge.registry;

import dev.obscuria.fragmentum.network.PayloadRegistrar;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;

import java.util.function.BiConsumer;
import java.util.function.Function;

public record ForgePayloadRegistrar(String modId) implements PayloadRegistrar
{
    @Override
    public void allowClientOnly()
    {
        ForgeNetworking.clientOnlyMods.add(modId);
    }

    @Override
    public void allowServerOnly()
    {
        ForgeNetworking.serverOnlyMods.add(modId);
    }

    @Override
    public <T> void registerClientbound(Class<T> clazz, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<Player, T> handler)
    {
        final var channel = ForgeNetworking.getOrCreateChannel(this.modId);
        ForgeNetworking.channelByType.put(clazz, channel);
        channel.messageBuilder(clazz, ForgeNetworking.nextIdFor(modId), NetworkDirection.PLAY_TO_CLIENT).encoder(encoder).decoder(decoder).consumerMainThread((payload, context) ->
        {
            ForgeNetworking.replyContext = context.get();
            handler.accept(Minecraft.getInstance().player, payload);
            ForgeNetworking.replyContext = null;
        }).add();
    }

    @Override
    public <T> void registerServerbound(Class<T> clazz, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<ServerPlayer, T> handler)
    {
        final var channel = ForgeNetworking.getOrCreateChannel(this.modId);
        ForgeNetworking.channelByType.put(clazz, channel);
        channel.messageBuilder(clazz, ForgeNetworking.nextIdFor(modId), NetworkDirection.PLAY_TO_SERVER).encoder(encoder).decoder(decoder).consumerMainThread((payload, context) ->
        {
            ForgeNetworking.replyContext = context.get();
            handler.accept(context.get().getSender(), payload);
            ForgeNetworking.replyContext = null;
        }).add();
    }
}
