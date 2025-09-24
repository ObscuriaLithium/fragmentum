package dev.obscuria.fragmentum;

import dev.obscuria.fragmentum.service.FragmentumServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

@SuppressWarnings("unused")
public interface Fragmentum
{
    String MOD_ID = "fragmentum";
    String MOD_NAME = "Fragmentum";
    Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    Platform PLATFORM = ServiceLoader.load(Platform.class).findFirst().orElseThrow();
    FragmentumServices SERVICES = ServiceLoader.load(FragmentumServices.class).findFirst().orElseThrow();

    static void init() {}
}