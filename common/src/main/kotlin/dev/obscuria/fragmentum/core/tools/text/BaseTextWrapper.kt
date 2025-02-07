package dev.obscuria.fragmentum.core.tools.text

import dev.obscuria.fragmentum.api.tools.text.TextWrapper
import io.netty.util.collection.IntObjectHashMap
import io.netty.util.collection.IntObjectMap
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import java.util.function.Function

class BaseTextWrapper(private val text: String) : TextWrapper
{
    private val prefixByLine: IntObjectMap<String> = IntObjectHashMap()
    private val styleByLine: IntObjectMap<Style> = IntObjectHashMap()
    private var fragment = Function<String, Component> { text: String -> Component.literal(text) }
    private var defaultPrefix = ""
    private var defaultStyle: Style = Style.EMPTY
    private var maxLength = 40

    override fun withMaxLength(length: Int): TextWrapper
    {
        this.maxLength = length
        return this
    }

    override fun withPrefix(prefix: String): TextWrapper
    {
        this.defaultPrefix = prefix
        return this
    }

    override fun withLinePrefix(index: Int, prefix: String): TextWrapper
    {
        prefixByLine.put(index, prefix)
        return this
    }

    override fun withStyle(style: Style): TextWrapper
    {
        this.defaultStyle = style
        return this
    }

    override fun withLineStyle(index: Int, style: Style): TextWrapper
    {
        styleByLine.put(index, style)
        return this
    }

    override fun fragment(function: Function<String, Component>): TextWrapper
    {
        this.fragment = function
        return this
    }

    override fun build(): List<Component>
    {
        return try
        {
            val result = mutableListOf<Component>()
            val words = text.replace(BREAKER, " #$# ").split(" ".toRegex()).filterNot { it.isBlank() }
            val iterator = words.iterator()

            while (iterator.hasNext())
            {
                val line = Component.literal(prefixByLine.getOrDefault(result.size, defaultPrefix))
                val firstWord = iterator.next()
                if (firstWord == "#$#")
                {
                    result.add(line.withStyle(styleByLine.getOrDefault(result.size, defaultStyle)))
                    continue
                }
                line.append(fragment.apply(firstWord))

                while (iterator.hasNext())
                {
                    val nextWord = iterator.next()
                    if (nextWord == "#$#")
                    {
                        iterator.next()
                        break
                    }
                    val finalWord = fragment.apply(nextWord)
                    if (getLength(line.string + " " + finalWord.string) > maxLength)
                    {
                        break
                    }
                    line.append(" ").append(finalWord)
                }

                result.add(line.withStyle(styleByLine.getOrDefault(result.size, defaultStyle)))
            }

            result
        }
        catch (e: Exception)
        {
            emptyList()
        }
    }

    companion object
    {
        private const val BREAKER = "\n"

        fun getLength(string: String?): Int
        {
            return ChatFormatting.stripFormatting(string)!!.length
        }
    }
}