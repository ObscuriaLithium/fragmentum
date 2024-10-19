package dev.obscuria.fragmentum.api.v1.client;

@SuppressWarnings("unused")
public interface FragmentumClientRegistry
{
    static IClientRegistrar registrar(String modId)
    {
        return V1Client.INSTANCE.registrar(modId);
    }
}
