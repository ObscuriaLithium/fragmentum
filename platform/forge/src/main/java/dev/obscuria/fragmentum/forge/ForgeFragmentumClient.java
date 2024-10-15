package dev.obscuria.fragmentum.forge;

import dev.obscuria.fragmentum.api.Fragmentum;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Fragmentum.MODID)
public final class ForgeFragmentumClient
{
    public ForgeFragmentumClient()
    {
        if (!FMLEnvironment.dist.isClient()) return;
    }
}