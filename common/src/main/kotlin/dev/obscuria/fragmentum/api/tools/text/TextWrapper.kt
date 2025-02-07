package dev.obscuria.fragmentum.api.tools.text

import dev.obscuria.fragmentum.api.Fragmentum
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import java.util.function.Function

interface TextWrapper
{
    fun withMaxLength(length: Int): TextWrapper

    fun withPrefix(prefix: String): TextWrapper

    fun withLinePrefix(index: Int, prefix: String): TextWrapper

    fun withStyle(style: Style): TextWrapper

    fun withLineStyle(index: Int, style: Style): TextWrapper

    fun fragment(function: Function<String, Component>): TextWrapper

    fun build(): List<Component>

    companion object
    {
        fun create(text: String): TextWrapper = Fragmentum.SERVICES.newTextWrapper(text)

        fun create(component: Component): TextWrapper = Fragmentum.SERVICES.newTextWrapper(component.string)
    }
}