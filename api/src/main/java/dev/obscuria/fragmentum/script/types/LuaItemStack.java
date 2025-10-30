package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.item.ItemStack;

@SuppressWarnings("all")
public class LuaItemStack extends LuaWrapper<ItemStack> {

    public static final LuaOps<ItemStack> OPS = null;
    public static final LuaOps.Nilable<ItemStack> NIL_OPS = null;

    public LuaItemStack(final ItemStack stack) {
        super(stack, null);
    }

    @Override
    protected void build(Builder builder) {}
}