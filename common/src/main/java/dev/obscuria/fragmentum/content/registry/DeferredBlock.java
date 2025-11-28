package dev.obscuria.fragmentum.content.registry;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class DeferredBlock<T extends Block> extends Deferred<Block, T> {

    public DeferredBlock(Supplier<Holder<Block>> supplier) {
        super(supplier);
    }

    public BlockState instantiate() {
        return get().defaultBlockState();
    }
}
