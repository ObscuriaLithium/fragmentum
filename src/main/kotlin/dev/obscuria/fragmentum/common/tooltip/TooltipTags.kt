package dev.obscuria.fragmentum.common.tooltip

import dev.obscuria.fragmentum.api.common.color.RGB
import dev.obscuria.fragmentum.api.common.color.rgbOf
import dev.obscuria.fragmentum.api.common.tooltip.StyleFlag
import net.minecraft.ChatFormatting
import org.jetbrains.annotations.ApiStatus
import kotlin.math.sin

@ApiStatus.Internal
internal object TooltipTags {

    private val registry = mutableMapOf<String, Instance>()
    private val startTime = System.currentTimeMillis()

    init {
        register("color", Color)
        register("chroma", Chroma)
        register("br", LineBreak)
        register("i", SimpleFlag(StyleFlag.ITALIC))
        register("b", SimpleFlag(StyleFlag.BOLD))
        register("no-i", SimpleFlag(StyleFlag.NON_ITALIC))
        register("no-b", SimpleFlag(StyleFlag.NON_BOLD))
    }

    fun register(key: String, instance: Instance) {
        registry.putIfAbsent(key, instance)
    }

    fun open(builder: TooltipBuilder, key: String, args: List<String>) {
        registry[key]?.open(builder, args)
    }

    fun close(builder: TooltipBuilder, key: String) {
        registry[key]?.close(builder)
    }

    interface Instance {
        fun open(builder: TooltipBuilder, args: List<String>)
        fun close(builder: TooltipBuilder)
    }

    private class SimpleFlag(private val flag: StyleFlag) : Instance {
        override fun open(builder: TooltipBuilder, args: List<String>) = builder.pushFlag(flag)
        override fun close(builder: TooltipBuilder) = builder.popFlag(flag)
    }

    private object LineBreak : Instance {
        override fun open(builder: TooltipBuilder, args: List<String>) = builder.breakLine()
        override fun close(builder: TooltipBuilder) = builder.breakLine()
    }

    private object Color : Instance {

        override fun open(builder: TooltipBuilder, args: List<String>) {
            val arg = args.firstOrNull() ?: return
            runCatching {
                if (arg.startsWith("#")) {
                    builder.pushColor(rgbOf(arg))
                } else {
                    val color = requireNotNull(ChatFormatting.getByName(arg)).color
                    builder.pushColor(rgbOf(color ?: 0xffffff))
                }
            }
        }

        override fun close(builder: TooltipBuilder) = builder.popColor()
    }

    private object Chroma : Instance {

        override fun open(builder: TooltipBuilder, args: List<String>) {
            runCatching {
                when {
                    args.size == 1 -> {
                        val preset = ChromaPresetsImpl.get(args[0]) ?: return
                        apply(builder, preset.first, preset.second, preset.speed)
                    }
                    args.size >= 2 -> {
                        val first = rgbOf(args[0])
                        val second = rgbOf(args[1])
                        val speed = if (args.size >= 3) args[2].toFloat() else 1f
                        apply(builder, first, second, speed)
                    }
                }
            }
        }

        override fun close(builder: TooltipBuilder) = builder.popColor()

        private fun apply(builder: TooltipBuilder, first: RGB, second: RGB, speed: Float) {
            val seconds = (System.currentTimeMillis() - startTime) / 1000f
            builder.pushColor(first.lerp(second, 0.5f + 0.5f * sin(seconds * speed)))
        }
    }
}
