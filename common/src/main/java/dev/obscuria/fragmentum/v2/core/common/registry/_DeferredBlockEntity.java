package dev.obscuria.fragmentum.v2.core.common.registry;

import dev.obscuria.fragmentum.v2.api.common.registry.DeferredBlockEntity;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class _DeferredBlockEntity<T extends BlockEntity> extends _Deferred<BlockEntityType<T>> implements DeferredBlockEntity<T> {

    public _DeferredBlockEntity(Holder<BlockEntityType<T>> holder) {
        super(holder);
    }
}
