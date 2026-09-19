package dev.obscuria.fragmentum.common.tooltip

object TooltipTemplates {

    private val registry = mutableMapOf<String, Processor>()

    fun register(key: String, processor: Processor) {
        registry.putIfAbsent(key, processor)
    }

    fun process(source: Any?, key: String, args: List<String>): String {
        val processor = registry[key] ?: return "🗙"
        return processor.process(source, args)
    }

    fun interface Processor {

        fun process(source: Any?, args: List<String>): String
    }
}
