package dev.obscuria.fragmentum.forge.service;

import dev.obscuria.fragmentum.forge.registry.ForgeRegistrar;
import dev.obscuria.fragmentum.registry.Registrar;
import dev.obscuria.fragmentum.service.*;

public final class ForgeServices implements FragmentumServices
{
    @Override
    public Registrar registrar(String modId)
    {
        return new ForgeRegistrar(modId);
    }

    @Override
    public FactoryService factory()
    {
        return ForgeFactoryService.INSTANCE;
    }

    @Override
    public NetworkService network()
    {
        return ForgeNetworkService.INSTANCE;
    }

    @Override
    public ServerService server()
    {
        return ForgeServerService.INSTANCE;
    }

    @Override
    public ClientService client()
    {
        return ForgeClientService.INSTANCE;
    }

    @Override
    public ConfigService config() {
        return ForgeConfigService.INSTANCE;
    }
}
