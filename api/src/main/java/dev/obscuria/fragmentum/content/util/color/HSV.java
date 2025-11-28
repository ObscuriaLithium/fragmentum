package dev.obscuria.fragmentum.content.util.color;

import com.mojang.serialization.Codec;
import lombok.With;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

@SuppressWarnings("ALL")
public record HSV(
        @With float hue,
        @With float saturation,
        @With float value)
{
    public static final Codec<HSV> CODEC = null;

    public List<Float> normalized()
    {
        throw new NotImplementedException();
    }

    public HSV shiftHue(float offset)
    {
        throw new NotImplementedException();
    }

    public HSV shiftSaturation(float offset)
    {
        throw new NotImplementedException();
    }

    public HSV shiftValue(float offset)
    {
        throw new NotImplementedException();
    }

    public HSV lerp(HSV to, float delta)
    {
        throw new NotImplementedException();
    }

    public RGB toRGB()
    {
        throw new NotImplementedException();
    }

    public ARGB toARGB(float alpha)
    {
        throw new NotImplementedException();
    }
}
