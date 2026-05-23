package dev.obscuria.fragmentum.v2.api.common.richtext;

import dev.obscuria.fragmentum.v2.api.common.Color;
import dev.obscuria.fragmentum.v2.core.common.richtext._ChromaPresets;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

@UtilityClass
@SuppressWarnings("unused")
public final class ChromaPresets {

    public static void register(String key, Instance preset) {
        _ChromaPresets.register(key, preset);
    }

    public static @Nullable Instance get(String key) {
        return _ChromaPresets.get(key);
    }

    public record Instance(Color first, Color second, float speed) {}
}
