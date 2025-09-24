package dev.obscuria.fragmentum.forge;

import dev.obscuria.fragmentum.Platform;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;

public final class ForgePlatform implements Platform
{
    @Override
    public String getEnvironmentName()
    {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public boolean isClient()
    {
        return FMLEnvironment.dist.isClient();
    }

    @Override
    public boolean isDedicatedServer()
    {
        return FMLEnvironment.dist.isDedicatedServer();
    }
}