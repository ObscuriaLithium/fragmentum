package dev.obscuria.fragmentum.script;

import org.apache.commons.lang3.NotImplementedException;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

@SuppressWarnings("all")
public record Script(
        Globals globals,
        LuaValue chunk
) {

    public void init() {
        throw new NotImplementedException();
    }
}
