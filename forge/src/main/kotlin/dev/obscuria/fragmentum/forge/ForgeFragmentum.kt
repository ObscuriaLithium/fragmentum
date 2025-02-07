package dev.obscuria.fragmentum.forge

import dev.obscuria.fragmentum.api.Fragmentum
import dev.obscuria.fragmentum.core.CoreFragmentum
import net.minecraftforge.fml.common.Mod

@Mod(Fragmentum.MODID)
class ForgeFragmentum
{
    init
    {
        Fragmentum.LOGGER.info("Hello Forge world from Kotlin!")
        CoreFragmentum.init()
    }
}