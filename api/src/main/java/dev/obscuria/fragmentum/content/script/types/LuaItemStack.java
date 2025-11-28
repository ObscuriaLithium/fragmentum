package dev.obscuria.fragmentum.content.script.types;

import net.minecraft.world.item.ItemStack;

@SuppressWarnings("all")
public class LuaItemStack extends LuaWrapper<ItemStack> {

    public static final LuaOps<ItemStack> OPS = null;
    public static final LuaOps.Nilable<ItemStack> NIL_OPS = null;

    public LuaItemStack(final ItemStack stack) {
        super(stack, OPS);
    }

    @Override
    protected void build(Builder builder) {

        builder.put("count", Property.of(LuaOps.INT, ItemStack::getCount, ItemStack::setCount));
        builder.put("damageValue", Property.of(LuaOps.INT, ItemStack::getDamageValue, ItemStack::setDamageValue));

        builder.put("isEmpty", method0(LuaOps.BOOL, ItemStack::isEmpty));
        builder.put("isStackable", method0(LuaOps.BOOL, ItemStack::isStackable));
        builder.put("isDamaged", method0(LuaOps.BOOL, ItemStack::isDamaged));
        builder.put("isDamageable", method0(LuaOps.BOOL, ItemStack::isDamageableItem));
        builder.put("getMaxStackSize", method0(LuaOps.INT, ItemStack::getMaxStackSize));
        builder.put("getMaxDamage", method0(LuaOps.INT, ItemStack::getMaxDamage));

        builder.put("grow", voidMethod1(LuaOps.INT, ItemStack::grow));
        builder.put("shrink", voidMethod1(LuaOps.INT, ItemStack::shrink));
        builder.put("split", method1(LuaOps.INT, LuaItemStack.OPS, ItemStack::split));
        builder.put("copyWithCount", method1(LuaOps.INT, LuaItemStack.OPS, ItemStack::copyWithCount));
    }
}