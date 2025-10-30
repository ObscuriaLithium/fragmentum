package dev.obscuria.fragmentum.script.types;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaValue;

public class LuaLivingEntity<T extends LivingEntity> extends LuaEntity<T> {

    public static final LuaOps<LivingEntity> OPS;
    public static final LuaOps.Nilable<LivingEntity> NIL_OPS;

    public LuaLivingEntity(T entity) {
        super(entity, LuaOps.nonnil(LuaLivingEntity::valueOf, LuaLivingEntity::nonnilSourceOf));
    }

    @Override
    protected void build(LuaWrapper<T>.Builder builder) {
        super.build(builder);
        builder.put("health", Property.of(LuaOps.FLOAT, LivingEntity::getHealth, LivingEntity::setHealth));
        builder.put("getHelmet", method0(LuaItemStack.OPS, LuaLivingEntity::getHelmet));
        builder.put("ignite", voidMethod1(LuaOps.INT, LivingEntity::setSecondsOnFire));
        builder.put("hurt", voidMethod2(LuaDamageSource.OPS, LuaOps.FLOAT, LivingEntity::hurt));
    }

    private static ItemStack getHelmet(LivingEntity entity) {
        return entity.getItemBySlot(EquipmentSlot.HEAD);
    }

    private static <T extends LivingEntity> LuaValue valueOf(@Nullable T entity) {
        return entity == null ? LuaValue.NIL : new LuaLivingEntity<>(entity);
    }

    @SuppressWarnings("unchecked")
    private static <T extends LivingEntity> @Nullable T nilableSourceOf(LuaValue value) {
        return value.isnil() ? null : ((LuaLivingEntity<T>) value).getSource();
    }

    @SuppressWarnings("unchecked")
    private static <T extends LivingEntity> T nonnilSourceOf(LuaValue value) {
        return ((LuaLivingEntity<T>) value).getSource();
    }

    static {
        OPS = LuaOps.nonnil(LuaLivingEntity::valueOf, LuaLivingEntity::nonnilSourceOf);
        NIL_OPS = LuaOps.nilable(LuaLivingEntity::valueOf, LuaLivingEntity::nilableSourceOf);

    }
}
