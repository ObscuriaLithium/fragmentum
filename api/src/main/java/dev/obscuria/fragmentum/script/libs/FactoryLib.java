package dev.obscuria.fragmentum.script.libs;

import dev.obscuria.fragmentum.script.types.LuaWrapper;

@SuppressWarnings("all")
public class FactoryLib extends LuaWrapper<Object> {

    public FactoryLib() {
        super(null, null);
    }

    @Override
    protected void build(Builder builder) {}
}
