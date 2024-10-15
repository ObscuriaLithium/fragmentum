package dev.obscuria.fragmentum.api.v1.common;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

public interface FragmentumFactory
{
    static <T extends BlockEntity> BlockEntityType.Builder<T>
    createBlockEntityType(BiFunction<BlockPos, BlockState, T> factory,
                          Block... blocks)
    {
        return V1Common.INSTANCE.createBlockEntityType(factory, blocks);
    }

    static <T extends ParticleOptions> ParticleType<T>
    createParticleType(boolean alwaysSpawn,
                       MapCodec<T> codec,
                       StreamCodec<RegistryFriendlyByteBuf, T> streamCodec)
    {
        return V1Common.INSTANCE.createParticleType(alwaysSpawn, codec, streamCodec);
    }
}
