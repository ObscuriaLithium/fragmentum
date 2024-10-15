package dev.obscuria.fragmentum.forge;

import dev.obscuria.fragmentum.api.Fragmentum;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;

@Mod(Fragmentum.MODID)
public final class ForgeFragmentum
{
    public ForgeFragmentum() {}

    public static IEventBus eventBus(String modId)
    {
        return ((FMLModContainer) ModList.get().getModContainerById(modId).orElseThrow()).getEventBus();
    }
}