package dev.obscuria.fragmentum.fabric.service

import com.mojang.serialization.Codec
import dev.obscuria.fragmentum.api.service.FactoryService
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
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

internal class SubFabricFactory : FactoryService
{
    override fun <T : BlockEntity> newBlockEntityType(
        factory: BiFunction<BlockPos, BlockState, T>,
        vararg blocks: Block
    ): BlockEntityType.Builder<T>
    {
        return BlockEntityType.Builder.of(factory::apply, *blocks)
    }

    override fun <T : ParticleOptions> newParticleType(
        alwaysSpawn: Boolean,
        codec: Codec<T>,
        deserializer: ParticleOptions.Deserializer<T>
    ): ParticleType<T>
    {
        return object : ParticleType<T>(alwaysSpawn, deserializer)
        {
            override fun codec(): Codec<T>
            {
                return codec
            }
        }
    }

    override fun newParticleType(alwaysSpawn: Boolean): SimpleParticleType
    {
        return FabricParticleTypes.simple(alwaysSpawn)
    }

    override fun <T : Sensor<*>?> newSensorType(factory: Supplier<T>): SensorType<T>
    {
        return SensorType(factory)
    }

    companion object
    {
        internal val INSTANCE = SubFabricFactory()
    }
}