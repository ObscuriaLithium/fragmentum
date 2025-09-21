package dev.obscuria.fragmentum.service

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

interface FactoryService {

    fun <T : BlockEntity> newBlockEntityType(
        factory: BiFunction<BlockPos, BlockState, T>,
        vararg blocks: Block
    ): BlockEntityType.Builder<T>

    fun <T : ParticleOptions> newParticleType(
        alwaysSpawn: Boolean,
        codec: Codec<T>,
        deserializer: ParticleOptions.Deserializer<T>
    ): ParticleType<T>

    fun newParticleType(alwaysSpawn: Boolean): SimpleParticleType

    fun <T : Sensor<*>?> newSensorType(factory: Supplier<T>): SensorType<T>
}