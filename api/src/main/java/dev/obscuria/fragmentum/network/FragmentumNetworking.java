package dev.obscuria.fragmentum.network;

import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("unused")
public interface FragmentumNetworking
{
    static PayloadRegistrar registrar(String modId)
    {
        throw new NotImplementedException();
    }

    static <T> void reply(T payload)
    {
        throw new NotImplementedException();
    }

    static <T> void sendTo(ServerPlayer player, T payload)
    {
        throw new NotImplementedException();
    }

    static <T> void sendToTracking(ServerLevel level, BlockPos pos, T payload)
    {
        throw new NotImplementedException();
    }

    static <T> void sendToTracking(Entity entity, T payload)
    {
        throw new NotImplementedException();
    }

    static <T> void sendToAll(MinecraftServer server, T payload)
    {
        throw new NotImplementedException();
    }

    static <T> void sendToServer(T payload)
    {
        throw new NotImplementedException();
    }
}
