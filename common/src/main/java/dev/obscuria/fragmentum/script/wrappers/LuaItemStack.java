package dev.obscuria.fragmentum.script.wrappers;

import net.minecraft.world.item.ItemStack;

public class LuaItemStack extends LuaWrapper<ItemStack> {

    public static final LuaOps<ItemStack> OPS;

    public LuaItemStack(final ItemStack stack) {
        super(stack);
    }

    @Override
    protected void build(Builder builder) {
        builder.put("isEmpty", method0(LuaOps.BOOL, ItemStack::isEmpty));
    }

    static {
        OPS = new LuaOps.Instance<>(LuaItemStack::new, value -> ((LuaItemStack) value).getSource());
    }
}