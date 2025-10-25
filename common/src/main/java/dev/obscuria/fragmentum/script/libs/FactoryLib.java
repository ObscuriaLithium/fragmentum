package dev.obscuria.fragmentum.script.libs;

import dev.obscuria.fragmentum.FragmentumProxy;
import dev.obscuria.fragmentum.script.types.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

@SuppressWarnings("all")
public class FactoryLib extends LuaWrapper<Object> {

    private static final Object DUMMY = new Object();

    public FactoryLib() {
        super(DUMMY);
    }

    @Override
    protected void build(Builder builder) {
        builder.put("resourceKeyOf", method2(LuaOps.STRING, LuaOps.STRING, LuaResourceKey.OPS, FactoryLib::resourceKeyOf));
        builder.put("damageSourceOf", method2(LuaResourceKey.OPS, LuaEntity.OPS, LuaDamageSource.OPS, FactoryLib::damageSourceOf));
    }

    @SuppressWarnings("unchecked")
    private static DamageSource damageSourceOf(Object self, ResourceKey<?> type, Entity attacker) {
        final var lookup = FragmentumProxy.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE);
        final var damageType = lookup.getOrThrow((ResourceKey<DamageType>) type);
        return new DamageSource(damageType, attacker);
    }

    private static ResourceKey<?> resourceKeyOf(Object self, String registry, String location) {
        final var registryId = new ResourceLocation(registry);
        final var locationId = new ResourceLocation(location);
        return ResourceKey.create(ResourceKey.createRegistryKey(registryId), locationId);
    }
}
