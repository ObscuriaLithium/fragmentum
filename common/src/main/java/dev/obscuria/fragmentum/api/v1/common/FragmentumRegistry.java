package dev.obscuria.fragmentum.api.v1.common;

@SuppressWarnings("unused")
public interface FragmentumRegistry
{
    static IRegistrar registrar(String modId)
    {
        return V1Common.INSTANCE.registrar(modId);
    }
}
