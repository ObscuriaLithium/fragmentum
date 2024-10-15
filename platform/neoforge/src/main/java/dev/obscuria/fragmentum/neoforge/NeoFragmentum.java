package dev.obscuria.fragmentum.neoforge;

import dev.obscuria.fragmentum.api.Fragmentum;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;

import java.util.Objects;

@Mod(Fragmentum.MODID)
public final class NeoFragmentum
{
    public NeoFragmentum(IEventBus eventBus) {}

    public static IEventBus eventBus(String modId)
    {
        return Objects.requireNonNull(ModList.get().getModContainerById(modId).orElseThrow().getEventBus());
    }
}