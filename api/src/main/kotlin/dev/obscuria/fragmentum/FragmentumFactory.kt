@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType", "DEPRECATION")

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
    ): BlockEntityType.Builder<T> = error("API stub")

    fun <T : ParticleOptions> newParticleType(
        alwaysSpawn: Boolean,
        codec: Codec<T>, deserializer:
        ParticleOptions.Deserializer<T>
    ): ParticleType<T> = error("API stub")

    fun newParticleType(alwaysSpawn: Boolean): SimpleParticleType = error("API stub")

    fun <T : Sensor<*>> newSensorType(factory: Supplier<T>): SensorType<T> = error("API stub")
}