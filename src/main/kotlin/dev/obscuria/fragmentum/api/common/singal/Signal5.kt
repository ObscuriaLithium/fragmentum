package dev.obscuria.fragmentum.api.common.singal

interface Signal5<P1, P2, P3, P4, P5> : Signal<Signal5.Listener<P1, P2, P3, P4, P5>> {

	fun emit(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5)

	fun interface Listener<P1, P2, P3, P4, P5> {

		fun consume(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5)
	}
}
