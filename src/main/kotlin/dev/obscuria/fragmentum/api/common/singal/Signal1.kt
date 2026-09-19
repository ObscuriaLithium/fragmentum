package dev.obscuria.fragmentum.api.common.singal

interface Signal1<P1> : Signal<Signal1.Listener<P1>> {

	fun emit(p1: P1)

	fun interface Listener<P1> {

		fun consume(p1: P1)
	}
}
