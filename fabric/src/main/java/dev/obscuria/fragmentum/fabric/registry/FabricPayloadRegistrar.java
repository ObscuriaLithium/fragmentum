package dev.obscuria.fragmentum.fabric.registry;

import dev.obscuria.fragmentum.Fragmentum;
import dev.obscuria.fragmentum.network.PayloadRegistrar;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.function.BiConsumer;
import java.util.function.Function;

public record FabricPayloadRegistrar(String modId) implements PayloadRegistrar
{
    @Override
    public void allowClientOnly() {}

    @Override
    public void allowServerOnly() {}

    @Override
    public <T> void registerClientbound(Class<T> clazz, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<Player, T> handler)
    {
        final var type = FabricNetworking.registerClientbound(modId, clazz, encoder, decoder);
        if (Fragmentum.PLATFORM.isDedicatedServer()) return;
        ClientPlayNetworking.registerGlobalReceiver(type, (packet, player, sender) -> {
            FabricNetworking.clientReplySender = sender;
            handler.accept(player, packet.payload());
            FabricNetworking.clientReplySender = null;
        });
    }

    @Override
    public <T> void registerServerbound(Class<T> clazz, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<ServerPlayer, T> handler)
    {
        final var type = FabricNetworking.registerServerbound(modId, clazz, encoder, decoder);
        ServerPlayNetworking.registerGlobalReceiver(type, (packet, player, sender) -> {
            FabricNetworking.serverReplySender = sender;
            handler.accept(player, packet.payload());
            FabricNetworking.serverReplySender = null;
        });
    }
}
