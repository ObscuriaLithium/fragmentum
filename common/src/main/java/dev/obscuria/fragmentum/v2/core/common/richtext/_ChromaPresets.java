package dev.obscuria.fragmentum.v2.core.common.richtext;

import dev.obscuria.fragmentum.Fragmentum;
import dev.obscuria.fragmentum.v2.api.common.Color;
import dev.obscuria.fragmentum.v2.api.common.richtext.ChromaPresets;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class _ChromaPresets {

    private static final Map<String, ChromaPresets.Instance> REGISTRY = new HashMap<>();

    private _ChromaPresets() {}

    public static void register(String key, ChromaPresets.Instance preset) {
        if (REGISTRY.putIfAbsent(key, preset) != null) {
            Fragmentum.LOG.warn("Chroma preset '{}' is already registered; ignoring duplicate.", key);
        }
    }

    public static @Nullable ChromaPresets.Instance get(String key) {
        return REGISTRY.get(key);
    }

    static {
        register("gold", new ChromaPresets.Instance(
                Color.parse("#e09704"),
                Color.parse("#ffc34a"),
                4f));
    }
}
