package dev.obscuria.fragmentum.v2.api.common.registry;

import dev.obscuria.fragmentum.v2.core.common.registry._DeferredBlockEntity;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

@SuppressWarnings("unused")
public interface DeferredBlockEntity<T extends BlockEntity> extends Deferred<BlockEntityType<T>> {

    static <T extends BlockEntity> DeferredBlockEntity<T> create(Holder<BlockEntityType<T>> holder) {
        return new _DeferredBlockEntity<>(holder);
    }
}
