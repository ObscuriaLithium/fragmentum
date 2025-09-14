package dev.obscuria.fragmentum.core.tools.text

import dev.obscuria.fragmentum.api.tools.text.TextWrapper
import io.netty.util.collection.IntObjectHashMap
import io.netty.util.collection.IntObjectMap
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style

class BaseTextWrapper(private val text: String) : TextWrapper {
    private val prefixByLine: IntObjectMap<String> = IntObjectHashMap()
    private val styleByLine: IntObjectMap<Style> = IntObjectHashMap()
    private var fragment: (String) -> Component = { Component.literal(it) }
    private var defaultPrefix = ""
    private var defaultStyle: Style = Style.EMPTY
    private var maxLength = 40

    override fun withMaxLength(length: Int): TextWrapper {
        this.maxLength = length
        return this
    }

    override fun withPrefix(prefix: String): TextWrapper {
        this.defaultPrefix = prefix
        return this
    }

    override fun withLinePrefix(index: Int, prefix: String): TextWrapper {
        prefixByLine.put(index, prefix)
        return this
    }

    override fun withStyle(style: Style): TextWrapper {
        this.defaultStyle = style
        return this
    }

    override fun withLineStyle(index: Int, style: Style): TextWrapper {
        styleByLine.put(index, style)
        return this
    }

    override fun fragment(function: (String) -> Component): TextWrapper {
        this.fragment = function
        return this
    }

    override fun build(): List<Component> {

        val result = ArrayList<Component>(8)

        var i = 0
        val length = text.length
        val wordBuffer = StringBuilder(16)
        val words = ArrayList<String>(length / 5)

        while (i < length) {
            when (val char = text[i++]) {
                ' ' -> flushWord(wordBuffer) { words.add(it) }
                '\n' -> {
                    flushWord(wordBuffer) { words.add(it) }
                    words.add("#$#")
                }

                else -> wordBuffer.append(char)
            }
        }

        flushWord(wordBuffer) { words.add(it) }

        val iter = words.listIterator()
        while (iter.hasNext()) {
            val prefix = prefixByLine.getOrDefault(result.size, defaultPrefix)
            val line = Component.literal(prefix)

            val firstWord = iter.next()
            if (firstWord == "#$#") {
                result.add(line.withStyle(styleByLine.getOrDefault(result.size, defaultStyle)))
                continue
            }
            line.append(fragment(firstWord))

            // Чтобы не пересоздавать строки, считаем длину вручную
            var currentLength = prefix.length + firstWord.length

            while (iter.hasNext()) {
                val nextWord = iter.next()
                if (nextWord == "#$#") break

                val wordLength = nextWord.length + 1 // +1 за пробел
                if (currentLength + wordLength > maxLength) {
                    iter.previous() // вернуть слово обратно
                    break
                }

                line.append(" ").append(fragment(nextWord))
                currentLength += wordLength
            }

            result.add(line.withStyle(styleByLine.getOrDefault(result.size, defaultStyle)))
        }

        return result
    }

    private inline fun flushWord(buffer: StringBuilder, consumer: (String) -> Unit) {
        if (buffer.isEmpty()) return
        consumer(buffer.toString())
        buffer.setLength(0)
    }

//    override fun build(): List<Component>
//    {
//        return try
//        {
//            val result = mutableListOf<Component>()
//            val words = text.replace(BREAKER, " #$# ").split(" ".toRegex()).filterNot { it.isBlank() }
//
//            val iterator = words.listIterator()
//            while (iterator.hasNext()) {
//                val line = Component.literal(prefixByLine.getOrDefault(result.size, defaultPrefix))
//                val firstWord = iterator.next()
//                if (firstWord == "#$#") {
//                    result.add(line.withStyle(styleByLine.getOrDefault(result.size, defaultStyle)))
//                    continue
//                }
//                line.append(fragment.apply(firstWord))
//
//                while (iterator.hasNext()) {
//                    val nextWord = iterator.next()
//                    if (nextWord == "#$#") {
//                        break
//                    }
//                    val finalWord = fragment.apply(nextWord)
//                    if (getLength(line.string + " " + finalWord.string) > maxLength) {
//                        iterator.previous()
//                        break
//                    }
//                    line.append(" ").append(finalWord)
//                }
//
//                result.add(line.withStyle(styleByLine.getOrDefault(result.size, defaultStyle)))
//            }
//
//            result
//        }
//        catch (e: Exception)
//        {
//            emptyList()
//        }
//    }

    companion object {
        private const val BREAKER = "\n"

        fun getLength(string: String?): Int {
            return ChatFormatting.stripFormatting(string)!!.length
        }
    }
}