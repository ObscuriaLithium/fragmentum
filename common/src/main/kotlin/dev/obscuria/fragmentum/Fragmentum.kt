@file:Suppress("unused", "RedundantNullableReturnType", "MemberVisibilityCanBePrivate")

package dev.obscuria.fragmentum

import dev.obscuria.fragmentum.world.AllOfTooltipComponent
import dev.obscuria.fragmentum.service.FragmentumServices
import dev.obscuria.fragmentum.tools.text.AutoDescription
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.world.inventory.tooltip.TooltipComponent
import net.minecraft.world.item.ItemStack
import org.apache.commons.compress.utils.Lists
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import java.util.function.Consumer

object Fragmentum {

    const val MODID: String = "fragmentum"
    const val DISPLAY_NAME: String = "Fragmentum"
    val LOGGER: Logger = LoggerFactory.getLogger(DISPLAY_NAME)
    val PLATFORM: Platform = ServiceLoader.load(Platform::class.java).first()

    internal val SERVICES: FragmentumServices = ServiceLoader.load(FragmentumServices::class.java).first()

    fun init() {}

    fun modifyTooltip(stack: ItemStack, consumer: Consumer<Component>) {
        if (!stack.item.javaClass.isAnnotationPresent(AutoDescription::class.java)) return
        val annotation = stack.item.javaClass.getAnnotation(AutoDescription::class.java)
        val id = BuiltInRegistries.ITEM.getKey(stack.item)
        val description = Component.translatable(id.toLanguageKey("item", "desc"))
//        val lines = TextWrapper.create(description)
//            .withStyle(Style.EMPTY.withColor(annotation.style))
//            .withMaxLength(40)
//            .build()
//        lines.forEach(consumer)
    }

    fun gatherTooltipImages(stack: ItemStack, defaultImage: Optional<TooltipComponent>): Optional<TooltipComponent> {
        val customImages = Lists.newArrayList<TooltipComponent>()
        FragmentumEvents.GATHER_TOOLTIP_IMAGES broadcast { listener -> listener.invoke(stack, customImages::add) }
        if (customImages.isEmpty()) return defaultImage

        defaultImage.ifPresent { image: TooltipComponent -> customImages.add(0, image) }
        return Optional.of<TooltipComponent>(AllOfTooltipComponent(customImages))
    }
}