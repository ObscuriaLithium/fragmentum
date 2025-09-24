package dev.obscuria.fragmentum.network;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

@SuppressWarnings("unused")
public interface FragmentumNetworking
{
    static PayloadRegistrar registrar(String modId)
    {
        return Fragmentum.SERVICES.network().payloadRegistrar(modId);
    }

    static <T> void reply(T payload)
    {
        Fragmentum.SERVICES.network().reply(payload);
    }

    static <T> void sendTo(ServerPlayer player, T payload)
    {
        Fragmentum.SERVICES.network().sendTo(player, payload);
    }

    static <T> void sendToTracking(ServerLevel level, BlockPos pos, T payload)
    {
        Fragmentum.SERVICES.network().sendToTracking(level, pos, payload);
    }

    static <T> void sendToTracking(Entity entity, T payload)
    {
        Fragmentum.SERVICES.network().sendToTracking(entity, payload);
    }

    static <T> void sendToAll(MinecraftServer server, T payload)
    {
        Fragmentum.SERVICES.network().sendToAll(server, payload);
    }

    static <T> void sendToServer(T payload)
    {
        Fragmentum.SERVICES.network().sendToServer(payload);
    }
}
