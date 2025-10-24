package dev.obscuria.fragmentum.script.wrappers;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class LuaLivingEntity<T extends LivingEntity> extends LuaEntity<T> {

    public LuaLivingEntity(T entity) {
        super(entity);
    }

    @Override
    protected void build(LuaWrapper<T>.Builder builder) {
        super.build(builder);
        builder.put("health", Property.of(LuaOps.FLOAT, LivingEntity::getHealth, LivingEntity::setHealth));
        builder.put("getHelmet", method0(LuaItemStack.OPS, LuaLivingEntity::getHelmet));
        builder.put("ignite", voidMethod1(LuaOps.INT, LivingEntity::setSecondsOnFire));
    }

    private static ItemStack getHelmet(LivingEntity entity) {
        return entity.getItemBySlot(EquipmentSlot.HEAD);
    }
}
