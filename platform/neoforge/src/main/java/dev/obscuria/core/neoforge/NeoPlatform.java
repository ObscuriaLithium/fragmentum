package dev.obscuria.fragmentum.neoforge;

import dev.obscuria.fragmentum.api.IPlatform;
import dev.obscuria.fragmentum.api.v1.common.config.IConfigBuilder;
import dev.obscuria.fragmentum.neoforge.config.NeoConfigBuilder;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class NeoPlatform implements IPlatform
{
    @Override
    public IConfigBuilder createConfig()
    {
        return new NeoConfigBuilder(new ModConfigSpec.Builder());
    }

    @Override
    public String getPlatformName()
    {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId)
    {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isClient()
    {
        return FMLEnvironment.dist.isClient();
    }

    @Override
    public boolean isDevelopmentEnvironment()
    {
        return !FMLLoader.isProduction();
    }
}
