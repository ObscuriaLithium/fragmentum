package dev.obscuria.fragmentum.fabric

import dev.obscuria.fragmentum.api.Platform
import net.fabricmc.api.EnvType
import net.fabricmc.loader.api.FabricLoader

class FabricPlatform : Platform
{
    override fun getPlatformName(): String
    {
        return "Fabric"
    }

    override fun isModLoaded(modId: String): Boolean
    {
        return FabricLoader.getInstance().isModLoaded(modId)
    }

    override fun isClient(): Boolean
    {
        return FabricLoader.getInstance().environmentType == EnvType.CLIENT
    }

    override fun isDevelopmentEnvironment(): Boolean
    {
        return FabricLoader.getInstance().isDevelopmentEnvironment
    }
}