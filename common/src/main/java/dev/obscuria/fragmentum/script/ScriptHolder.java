package dev.obscuria.fragmentum.script;

import com.mojang.serialization.Codec;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaValue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScriptHolder {

    public static final Codec<ScriptHolder> CODEC = ResourceLocation.CODEC.xmap(ScriptHolder::getOrCreate, ScriptHolder::getKey);

    private static final Map<ResourceLocation, ScriptHolder> POOL = new ConcurrentHashMap<>();

    @Getter private final ResourceLocation key;
    @Getter private @Nullable Script script;

    public static ScriptHolder getOrCreate(ResourceLocation key) {
        return POOL.computeIfAbsent(key, value -> new ScriptHolder(key, null));
    }

    public static ScriptHolder getOrCreate(ResourceLocation key, Script script) {
        return POOL.computeIfAbsent(key, value -> new ScriptHolder(key, script));
    }

    public ScriptHolder swapTo(Script script) {
        this.script = script;
        return this;
    }

    public LuaValue getValue(String value) {
        if (this.script == null) throw new IllegalStateException("Script is not loaded");
        return this.script.globals().get(value);
    }

    public LuaValue call(String function, LuaValue arg) {
        return getValue(function).call(arg);
    }

    public LuaValue call(String function, LuaValue argA, LuaValue argB) {
        return getValue(function).call(argA, argB);
    }

    public LuaValue call(String function, LuaValue argA, LuaValue argB, LuaValue argC) {
        return getValue(function).call(argA, argB, argC);
    }
}
