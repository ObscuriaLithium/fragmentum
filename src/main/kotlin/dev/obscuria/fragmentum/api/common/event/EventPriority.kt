package dev.obscuria.fragmentum.api.common.event

enum class EventPriority(val value: Int) {
	/** Runs before every other priority level. Use for setup or validation listeners that must execute first. */
	HIGHEST(0),

	/** Runs after [HIGHEST] but before [DEFAULT]. */
	HIGH(500),

	/** The default priority used by [Event.register] when none is specified. */
	DEFAULT(1000),

	/** Runs after [DEFAULT] but before [LOWEST]. */
	LOW(1500),

	/** Runs after every other priority level and observes the final state left by all preceding listeners. */
	LOWEST(2000)
}
