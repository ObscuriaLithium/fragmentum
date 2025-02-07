package dev.obscuria.fragmentum.core.client

import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.world.inventory.tooltip.TooltipComponent
import org.apache.commons.compress.utils.Lists
import java.util.*
import java.util.function.Function

object TooltipComponentRegistry
{
    private val definitions: MutableList<Definition<*>> = Lists.newArrayList()

    @JvmStatic
    fun <T : TooltipComponent> register(type: Class<T>, factory: Function<T, ClientTooltipComponent>)
    {
        if (definitions.stream().anyMatch { definition: Definition<*> -> definition.type == type }) return
        definitions.add(Definition(type, factory))
    }

    @JvmStatic
    fun create(component: TooltipComponent): Optional<ClientTooltipComponent>
    {
        for (definition in definitions)
            if (definition.isFor(component))
                return definition.create(component)
        return Optional.empty()
    }

    @JvmRecord
    private data class Definition<T : TooltipComponent>(
        val type: Class<T>,
        val factory: Function<T, ClientTooltipComponent>
    )
    {
        fun isFor(component: TooltipComponent): Boolean
        {
            return component.javaClass == type
        }

        fun create(component: TooltipComponent): Optional<ClientTooltipComponent>
        {
            return Optional.of(
                factory.apply(type.cast(component))
            )
        }
    }
}