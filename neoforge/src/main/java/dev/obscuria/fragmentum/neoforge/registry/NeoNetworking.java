package dev.obscuria.fragmentum.neoforge.registry;

import com.google.common.collect.Sets;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Set;
import java.util.function.Consumer;

public final class NeoNetworking {

    public static final Set<String> clientOnlyMods = Sets.newConcurrentHashSet();
    public static final Set<String> serverOnlyMods = Sets.newConcurrentHashSet();
    public static @Nullable Consumer<CustomPacketPayload> replyConsumer;
}
