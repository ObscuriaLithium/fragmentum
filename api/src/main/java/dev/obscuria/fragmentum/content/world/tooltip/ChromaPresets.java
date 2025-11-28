package dev.obscuria.fragmentum.content.world.tooltip;

import dev.obscuria.fragmentum.content.util.color.RGB;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class ChromaPresets {

    public static void register(String key, Instance preset) {
        throw new NotImplementedException();
    }

    public static @Nullable Instance get(String key) {
        throw new NotImplementedException();
    }

    public record Instance(RGB first, RGB second, float speed) {}
}