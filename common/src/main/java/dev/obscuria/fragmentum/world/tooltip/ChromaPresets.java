package dev.obscuria.fragmentum.world.tooltip;

import dev.obscuria.fragmentum.util.color.Colors;
import dev.obscuria.fragmentum.util.color.RGB;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ChromaPresets {

    private static final Map<String, Instance> REGISTRY = new HashMap<>();

    static {
        register("gold", new Instance(Colors.rgbOf("#e09704"), Colors.rgbOf("#ffc34a"), 4f));
    }

    public static void register(String key, Instance preset) {
        REGISTRY.putIfAbsent(key, preset);
    }

    public static @Nullable Instance get(String key) {
        return REGISTRY.get(key);
    }

    public record Instance(RGB first, RGB second, float speed) {}
}