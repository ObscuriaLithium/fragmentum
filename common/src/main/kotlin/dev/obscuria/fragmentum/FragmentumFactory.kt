@file:Suppress("unused", "RedundantNullableReturnType", "DEPRECATION")

package dev.obscuria.fragmentum

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.world.entity.ai.sensing.Sensor
import net.minecraft.world.entity.ai.sensing.SensorType
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import java.util.function.BiFunction
import java.util.function.Supplier

object FragmentumFactory {

    fun <T : BlockEntity> newBlockEntityType(
        factory: BiFunction<BlockPos, BlockState, T>,
        vararg blocks: Block
    ): BlockEntityType.Builder<T> {
        return Fragmentum.SERVICES.factory().newBlockEntityType(factory, *blocks)
    }

    fun <T : ParticleOptions> newParticleType(
        alwaysSpawn: Boolean,
        codec: Codec<T>, deserializer:
        ParticleOptions.Deserializer<T>
    ): ParticleType<T> {
        return Fragmentum.SERVICES.factory().newParticleType(alwaysSpawn, codec, deserializer)
    }

    fun newParticleType(alwaysSpawn: Boolean): SimpleParticleType {
        return Fragmentum.SERVICES.factory().newParticleType(alwaysSpawn)
    }

    fun <T : Sensor<*>> newSensorType(factory: Supplier<T>): SensorType<T> {
        return Fragmentum.SERVICES.factory().newSensorType(factory)
    }
}