package dev.obscuria.fragmentum.v2.api.common.registry;

import dev.obscuria.fragmentum.v2.core.common.registry._DeferredBlock;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("unused")
public interface DeferredBlock<T extends Block> extends Deferred<T> {

    static <T extends Block> DeferredBlock<T> create(Holder<T> holder) {
        return new _DeferredBlock<>(holder);
    }

    BlockState instantiate();
}
