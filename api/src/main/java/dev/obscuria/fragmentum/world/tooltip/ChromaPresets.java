package dev.obscuria.fragmentum.world.tooltip;

import dev.obscuria.fragmentum.util.color.RGB;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

public class ChromaPresets {

    public static void register(String key, Instance preset) {
        throw new NotImplementedException();
    }

    public static @Nullable Instance get(String key) {
        throw new NotImplementedException();
    }

    public record Instance(RGB first, RGB second, float speed) {}
}