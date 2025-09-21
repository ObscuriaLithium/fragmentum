@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.tools.easing

import com.mojang.serialization.Codec
import net.minecraft.util.StringRepresentable

enum class Easing : EasingFunction, StringRepresentable {

    LINEAR,
    CEIL,
    FLOOR,

    EASE_IN_SINE,
    EASE_IN_CIRCLE,
    EASE_IN_QUAD,
    EASE_IN_CUBIC,
    EASE_IN_QUART,
    EASE_IN_QUINT,
    EASE_IN_EXPO,
    EASE_IN_BACK,
    EASE_IN_ELASTIC,
    EASE_IN_BOUNCE,

    EASE_OUT_SINE,
    EASE_OUT_CIRCLE,
    EASE_OUT_QUAD,
    EASE_OUT_CUBIC,
    EASE_OUT_QUART,
    EASE_OUT_QUINT,
    EASE_OUT_EXPO,
    EASE_OUT_BACK,
    EASE_OUT_ELASTIC,
    EASE_OUT_BOUNCE,

    EASE_IN_OUT_SINE,
    EASE_IN_OUT_CIRCLE,
    EASE_IN_OUT_QUAD,
    EASE_IN_OUT_CUBIC,
    EASE_IN_OUT_QUART,
    EASE_IN_OUT_QUINT,
    EASE_IN_OUT_EXPO,
    EASE_IN_OUT_BACK,
    EASE_IN_OUT_ELASTIC,
    EASE_IN_OUT_BOUNCE,

    EASE_OUT_IN_SINE,
    EASE_OUT_IN_CIRCLE,
    EASE_OUT_IN_QUAD,
    EASE_OUT_IN_CUBIC,
    EASE_OUT_IN_QUART,
    EASE_OUT_IN_QUINT,
    EASE_OUT_IN_EXPO,
    EASE_OUT_IN_BACK,
    EASE_OUT_IN_ELASTIC,
    EASE_OUT_IN_BOUNCE;

    override fun compute(delta: Float): Float = error("API stub")

    override fun getSerializedName(): String = error("API stub")

    companion object {

        val CODEC: Codec<Easing> = error("API stub")
    }
}