package dev.obscuria.fragmentum.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

public final class Fragmentum
{
    public static final String MODID = "fragmentum";
    public static final String DISPLAY_NAME = "Fragmentum";
    public static final Logger LOG = LoggerFactory.getLogger(DISPLAY_NAME);
    public static final IPlatform PLATFORM = ServiceLoader.load(IPlatform.class).findFirst().orElseThrow();
}