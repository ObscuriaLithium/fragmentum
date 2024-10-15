package dev.obscuria.fragmentum.neoforge;

import dev.obscuria.fragmentum.api.ObscureAPI;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;

import java.util.Objects;

@Mod(ObscureAPI.MODID)
public final class NeoObscureAPI
{
    public NeoObscureAPI(IEventBus eventBus) {}

    public static IEventBus eventBus(String modId)
    {
        return Objects.requireNonNull(ModList.get().getModContainerById(modId).orElseThrow().getEventBus());
    }
}