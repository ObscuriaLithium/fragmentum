package dev.obscuria.fragmentum.content.script.libs;

import dev.obscuria.fragmentum.content.script.types.*;
import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("all")
public class FactoryLib extends LuaWrapper<Object> {

    public FactoryLib() {
        super(null, null);
    }

    @Override
    protected void build(Builder builder) {
        throw new NotImplementedException();
    }
}
