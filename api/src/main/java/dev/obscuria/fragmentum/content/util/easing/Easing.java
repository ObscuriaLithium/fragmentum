package dev.obscuria.fragmentum.content.util.easing;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("ALL")
public enum Easing implements EasingFunction, StringRepresentable
{
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

    public static final Codec<Easing> CODEC = null;

    @Override
    public float compute(float delta)
    {
        throw new NotImplementedException();
    }

    @Override
    public String getSerializedName()
    {
        throw new NotImplementedException();
    }
}