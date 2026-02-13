package dev.obscuria.fragmentum.content.script.types;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("all")
public class LuaLivingEntity<T extends LivingEntity> extends LuaEntity<T> {

    public static final LuaOps<LivingEntity> OPS = null;
    public static final LuaOps.Nilable<LivingEntity> NIL_OPS = null;

    public LuaLivingEntity(T entity) {
        super(entity, null);
    }

    @Override
    protected void build(LuaWrapper<T>.Builder builder) {
        super.build(builder);
        builder.put("health", Property.of(LuaOps.FLOAT, LivingEntity::getHealth, LivingEntity::setHealth));
        builder.put("getHelmet", method0(LuaItemStack.OPS, LuaLivingEntity::getHelmet));
        builder.put("ignite", voidMethod1(LuaOps.FLOAT, LivingEntity::igniteForSeconds));
        builder.put("hurt", voidMethod2(LuaDamageSource.OPS, LuaOps.FLOAT, LivingEntity::hurt));
    }

    private static ItemStack getHelmet(LivingEntity entity) {
        throw new NotImplementedException();
    }
}
