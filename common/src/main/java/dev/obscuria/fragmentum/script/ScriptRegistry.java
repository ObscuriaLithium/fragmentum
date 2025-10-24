package dev.obscuria.fragmentum.script;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ScriptRegistry {

    private static final Map<ResourceLocation, ScriptHolder> REGISTRY = new ConcurrentHashMap<>();
    private static final List<ResourceLocation> updatedKeys = new ArrayList<>();

    public static void register(ResourceLocation id, Script script) {
        updatedKeys.add(id);
        REGISTRY.compute(id, (key, old) -> old == null
                ? ScriptHolder.getOrCreate(key, script)
                : old.swapTo(script));
    }

    public static @Nullable ScriptHolder get(ResourceLocation key) {
        return REGISTRY.get(key);
    }

    public static ScriptHolder getOrThrow(ResourceLocation key) {
        final @Nullable var result = get(key);
        if (result == null) throw new IllegalArgumentException("Unknown script: " + key);
        return result;
    }

    static void onReloadStart() {
        updatedKeys.clear();
    }

    static void onReloadEnd() {
        REGISTRY.keySet().removeIf(key -> !updatedKeys.contains(key));
    }
}
