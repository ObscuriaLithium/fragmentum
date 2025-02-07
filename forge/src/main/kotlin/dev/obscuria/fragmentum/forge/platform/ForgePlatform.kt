package dev.obscuria.fragmentum.forge.platform

import dev.obscuria.fragmentum.api.Platform
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.loading.FMLEnvironment
import net.minecraftforge.fml.loading.FMLLoader

class ForgePlatform : Platform
{
    override fun getPlatformName(): String
    {
        return "Forge"
    }

    override fun isModLoaded(modId: String): Boolean
    {
        return ModList.get().isLoaded(modId)
    }

    override fun isClient(): Boolean
    {
        return FMLEnvironment.dist.isClient
    }

    override fun isDevelopmentEnvironment(): Boolean
    {
        return !FMLLoader.isProduction()
    }
}