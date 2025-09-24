package dev.obscuria.fragmentum.forge.registry;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class ForgeNetworking
{
    public static final Set<String> clientOnlyMods = Sets.newConcurrentHashSet();
    public static final Set<String> serverOnlyMods = Sets.newConcurrentHashSet();
    public static final Map<String, SimpleChannel> channelByMod = Maps.newConcurrentMap();
    public static final Map<Class<?>, SimpleChannel> channelByType = Maps.newConcurrentMap();
    public static final Map<String, Integer> idByMod = Maps.newConcurrentMap();
    public static NetworkEvent.@Nullable Context replyContext;

    public static SimpleChannel getOrCreateChannel(String modId)
    {
        return channelByMod.computeIfAbsent(modId, key ->
        {
            final var channelName = new ResourceLocation(key, "network");
            var builder = NetworkRegistry.ChannelBuilder.named(channelName);
            if (serverOnlyMods.contains(modId)) builder.clientAcceptedVersions(version -> true);
            if (clientOnlyMods.contains(modId)) builder.serverAcceptedVersions(version -> true);
            return builder.simpleChannel();
        });
    }

    public static SimpleChannel channelFor(Object payload)
    {
        return Objects.requireNonNull(channelByType.get(payload.getClass()));
    }

    public static int nextIdFor(String modId)
    {
        return idByMod.compute(modId, (key, prev) -> prev != null ? prev + 1 : 0);
    }
}
