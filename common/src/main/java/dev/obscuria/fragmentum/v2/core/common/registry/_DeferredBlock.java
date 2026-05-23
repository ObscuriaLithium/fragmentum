package dev.obscuria.fragmentum.v2.core.common.registry;

import dev.obscuria.fragmentum.v2.api.common.registry.DeferredBlock;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public final class _DeferredBlock<T extends Block> extends _Deferred<T> implements DeferredBlock<T> {

    public _DeferredBlock(Holder<T> holder) {
        super(holder);
    }

    @Override public BlockState instantiate() {
        return get().defaultBlockState();
    }
}
