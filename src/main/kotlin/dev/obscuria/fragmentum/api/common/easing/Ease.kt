package dev.obscuria.fragmentum.api.common.easing

fun interface Ease {

	fun apply(progress: Float): Float

	fun reverse() = Ease {
		1f - apply(it)
	}

	fun scale(scale: Float) = Ease {
		apply(it * (1f / scale))
	}

	fun merge(other: Ease, ratio: Float) = Ease {
		if (it <= ratio) {
			val local = it / ratio
			return@Ease apply(local) * ratio
		} else {
			val local = (it - ratio) / (1f - ratio)
			return@Ease ratio + other.apply(local) * (1f - ratio)
		}
	}

	fun mergeOut(other: Ease, ratio: Float) = Ease {
		if (it <= ratio) {
			val local = it / ratio
			return@Ease apply(local)
		} else {
			val local = (it - ratio) / (1f - ratio)
			return@Ease 1f - other.apply(local)
		}
	}
}
