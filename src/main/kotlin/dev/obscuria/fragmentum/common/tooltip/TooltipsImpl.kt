package dev.obscuria.fragmentum.common.tooltip

import dev.obscuria.fragmentum.api.common.tooltip.TooltipOptions
import net.minecraft.network.chat.Component
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal object TooltipsImpl {

    private const val SPACING = ' '
    private const val DELIMITER = ", "
    private const val CLOSING_MARKER = "/"

    private val TAG_PATTERN = Regex("""\[(/?)([\w-]+)(?:=([^]\[]+))?]""")
    private val TEMPLATE_PATTERN = Regex("""<([\w-]+)=([^>]*)>""")

    fun process(input: String, source: Any?, options: TooltipOptions): List<Component> {
        val builder = TooltipBuilder(options)
        val nodes = parseNodes(input, source)
        nodes.forEach { it.process(builder, options) }
        return builder.result()
    }

    private fun parseNodes(input: String, source: Any?): List<Node> {
        val group = mutableListOf<Node>()
        val nodes = mutableListOf<Node>()

        val matches = (
            TAG_PATTERN.findAll(input).map { MatchInfo(it.range.first, it.range.last + 1, true, it) } +
                TEMPLATE_PATTERN.findAll(input).map { MatchInfo(it.range.first, it.range.last + 1, false, it) }
            ).sortedBy { it.start }

        var lastIndex = 0
        for (match in matches) {
            val before = input.substring(lastIndex, match.start)
            if (before.isNotEmpty()) {
                val shouldGroupFirst = nodes.isNotEmpty() && !before.startsWith(SPACING)
                val shouldGroupLast = !before.endsWith(SPACING)
                val elements = before.split(SPACING)
                for (i in elements.indices) {
                    val element = elements[i]
                    if (element.isEmpty()) continue
                    when {
                        shouldGroupFirst && i == 0 -> group.add(Node.Text(element))
                        shouldGroupLast && i == elements.lastIndex -> {
                            if (element.length > 1) flushGroup(group, nodes)
                            group.add(Node.Text(element))
                        }
                        else -> {
                            flushGroup(group, nodes)
                            nodes.add(Node.Text(element))
                        }
                    }
                }
            }

            if (match.isTag) {
                val key = match.result.groups[2]!!.value
                if (match.result.groups[1]!!.value != CLOSING_MARKER) {
                    val args = match.result.groups[3]?.value
                        ?.split(DELIMITER)
                        ?.filter { it.isNotEmpty() }
                        ?: emptyList()
                    group.add(Node.OpeningTag(key, args))
                } else {
                    group.add(Node.ClosingTag(key))
                }
            } else {
                val key = match.result.groups[1]!!.value
                val args = match.result.groups[2]!!.value.split(DELIMITER).filter { it.isNotEmpty() }
                group.add(Node.Template(source, key, args))
            }

            lastIndex = match.end
        }

        val remaining = input.substring(lastIndex)
        if (remaining.isNotEmpty()) {
            val shouldGroupFirst = !remaining.startsWith(SPACING)
            val elements = remaining.split(SPACING)
            for (i in elements.indices) {
                val element = elements[i]
                if (element.isEmpty()) continue
                if (shouldGroupFirst && i == 0) {
                    group.add(Node.Text(element))
                } else {
                    flushGroup(group, nodes)
                    nodes.add(Node.Text(element))
                }
            }
        }

        flushGroup(group, nodes)
        return nodes
    }

    private fun flushGroup(group: MutableList<Node>, nodes: MutableList<Node>) {
        if (group.isEmpty()) return
        if (group.size == 1) {
            nodes.add(group.removeAt(0))
        } else {
            nodes.add(Node.Group(group.toList()))
            group.clear()
        }
    }

    private class MatchInfo(val start: Int, val end: Int, val isTag: Boolean, val result: MatchResult)

    private sealed class Node {

        abstract val length: Int

        abstract fun process(builder: TooltipBuilder, options: TooltipOptions, grouped: Boolean)

        fun process(builder: TooltipBuilder, options: TooltipOptions) = process(builder, options, false)

        class Text(private val content: String) : Node() {

            override val length: Int get() = content.length

            override fun process(builder: TooltipBuilder, options: TooltipOptions, grouped: Boolean) {
                if (!grouped) {
                    builder.prepareForAppend(length)
                    builder.maybeAppendSpacing()
                }
                builder.append(length, options.processor(content))
            }
        }

        class Template(
            private val source: Any?,
            private val key: String,
            private val args: List<String>
        ) : Node() {

            private var resolvedText: String? = null

            override val length: Int get() = resolveText().length

            private fun resolveText(): String =
                resolvedText ?: TooltipTemplates.process(source, key, args).also { resolvedText = it }

            override fun process(builder: TooltipBuilder, options: TooltipOptions, grouped: Boolean) {
                val text = resolveText()
                if (!grouped) {
                    builder.prepareForAppend(text.length)
                    builder.maybeAppendSpacing()
                }
                builder.append(text.length, Component.literal(text))
            }
        }

        class OpeningTag(private val key: String, private val args: List<String>) : Node() {

            override val length = 0

            override fun process(builder: TooltipBuilder, options: TooltipOptions, grouped: Boolean) {
                TooltipTags.open(builder, key, args)
            }
        }

        class ClosingTag(private val key: String) : Node() {

            override val length = 0

            override fun process(builder: TooltipBuilder, options: TooltipOptions, grouped: Boolean) {
                TooltipTags.close(builder, key)
            }
        }

        class Group(private val nodes: List<Node>) : Node() {

            override val length: Int get() = nodes.sumOf { it.length }

            override fun process(builder: TooltipBuilder, options: TooltipOptions, grouped: Boolean) {
                if (length > 0) {
                    builder.prepareForAppend(length)
                    builder.maybeAppendSpacing()
                }
                nodes.forEach { it.process(builder, options, true) }
            }
        }
    }
}
