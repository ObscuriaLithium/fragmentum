package dev.obscuria.fragmentum.script;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

public record Script(
        Globals globals,
        LuaValue chunk
) {

    public void init() {
        this.chunk.call();
    }
}
