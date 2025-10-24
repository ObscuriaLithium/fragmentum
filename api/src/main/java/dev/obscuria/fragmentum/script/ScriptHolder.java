package dev.obscuria.fragmentum.script;

import com.mojang.serialization.Codec;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.NotImplementedException;
import org.luaj.vm2.LuaValue;

@SuppressWarnings("all")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScriptHolder {

    public static final Codec<ScriptHolder> CODEC = null;

    public static ScriptHolder getOrCreate(ResourceLocation key) {
        throw new NotImplementedException();
    }

    public static ScriptHolder getOrCreate(ResourceLocation key, Script script) {
        throw new NotImplementedException();
    }

    public LuaValue getValue(String value) {
        throw new NotImplementedException();
    }

    public LuaValue call(String function, LuaValue arg) {
        throw new NotImplementedException();
    }

    public LuaValue call(String function, LuaValue argA, LuaValue argB) {
        throw new NotImplementedException();
    }

    public LuaValue call(String function, LuaValue argA, LuaValue argB, LuaValue argC) {
        throw new NotImplementedException();
    }
}
