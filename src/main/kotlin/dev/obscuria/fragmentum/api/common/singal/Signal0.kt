package dev.obscuria.fragmentum.api.common.singal

interface Signal0 : Signal<Signal0.Listener> {

	fun emit()

	fun interface Listener {

		fun consume()
	}
}
