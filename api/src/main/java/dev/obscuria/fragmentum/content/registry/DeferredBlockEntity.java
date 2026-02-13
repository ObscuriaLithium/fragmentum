package dev.obscuria.fragmentum.content.registry;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class DeferredBlockEntity<T extends BlockEntity> extends Deferred<BlockEntityType<T>> {

    public DeferredBlockEntity(Holder<BlockEntityType<T>> holder) {
        super(holder);
    }
}
