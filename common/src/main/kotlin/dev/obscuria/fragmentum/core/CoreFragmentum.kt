package dev.obscuria.fragmentum.core

import dev.obscuria.fragmentum.api.FragmentumEvents
import dev.obscuria.fragmentum.api.tools.text.AutoDescription
import dev.obscuria.fragmentum.api.tools.text.TextWrapper
import dev.obscuria.fragmentum.core.world.AllOfTooltipComponent
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.world.inventory.tooltip.TooltipComponent
import net.minecraft.world.item.ItemStack
import org.apache.commons.compress.utils.Lists
import org.jetbrains.annotations.ApiStatus.Internal
import java.util.*
import java.util.function.Consumer

@Internal
object CoreFragmentum
{
    fun init()
    {
    }

    @JvmStatic
    fun modifyTooltip(stack: ItemStack, consumer: Consumer<Component>)
    {
        if (!stack.item.javaClass.isAnnotationPresent(AutoDescription::class.java)) return
        val annotation = stack.item.javaClass.getAnnotation(AutoDescription::class.java)
        val id = BuiltInRegistries.ITEM.getKey(stack.item)
        val description = Component.translatable(id.toLanguageKey("item", "desc"))
        val lines = TextWrapper.create(description)
            .withStyle(Style.EMPTY.withColor(annotation.style))
            .withMaxLength(40)
            .build()
        lines.forEach(consumer)
    }

    @JvmStatic
    fun gatherTooltipImages(stack: ItemStack, defaultImage: Optional<TooltipComponent>): Optional<TooltipComponent>
    {
        val customImages = Lists.newArrayList<TooltipComponent>()
        FragmentumEvents.GATHER_TOOLTIP_IMAGES broadcast { listener -> listener.invoke(stack, customImages::add) }
        if (customImages.isEmpty()) return defaultImage

        defaultImage.ifPresent { image: TooltipComponent -> customImages.add(0, image) }
        return Optional.of<TooltipComponent>(AllOfTooltipComponent(customImages))
    }
}