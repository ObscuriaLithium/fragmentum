package dev.obscuria.fragmentum.registry;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class DeferredBlockEntity<T extends BlockEntity> extends Deferred<BlockEntityType<?>, BlockEntityType<T>>
{
    public DeferredBlockEntity(Supplier<Holder<BlockEntityType<?>>> supplier)
    {
        super(supplier);
    }
}
