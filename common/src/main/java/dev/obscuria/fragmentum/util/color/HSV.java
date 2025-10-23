package dev.obscuria.fragmentum.util.color;

import com.mojang.serialization.Codec;
import lombok.With;
import net.minecraft.util.Mth;

import java.util.List;

@SuppressWarnings("unused")
public record HSV(
        @With float hue,
        @With float saturation,
        @With float value)
{
    public static final Codec<HSV> CODEC = Codec.FLOAT.listOf().xmap(Colors::hsvOf, HSV::normalized);

    public List<Float> normalized()
    {
        return List.of(hue, saturation, value);
    }

    public HSV shiftHue(float offset)
    {
        var newHue = (hue + offset) % 1f;
        if (newHue < 0f) newHue += 1f;
        return new HSV(newHue, saturation, value);
    }

    public HSV shiftSaturation(float offset)
    {
        var newSaturation = (saturation + offset) % 1f;
        if (newSaturation < 0f) newSaturation += 1f;
        return new HSV(hue, newSaturation, value);
    }

    public HSV shiftValue(float offset)
    {
        var newValue = (value + offset) % 1f;
        if (newValue < 0f) newValue += 1f;
        return new HSV(hue, saturation, newValue);
    }

    public HSV lerp(HSV to, float delta)
    {
        return new HSV(
                Mth.lerp(delta, hue, to.hue),
                Mth.lerp(delta, saturation, to.saturation),
                Mth.lerp(delta, value, to.value));
    }

    public RGB toRGB()
    {
        int hueSegment = (int) (hue * 6f);
        float chroma = value * saturation;
        float secondary = chroma * (1f - Math.abs((hue * 6f) % 2f - 1f));
        float match = value - chroma;

        float red = 0f;
        float green = 0f;
        float blue = 0f;

        switch (hueSegment) {
            case 0:
                red = chroma;
                green = secondary;
                break;
            case 1:
                red = secondary;
                green = chroma;
                break;
            case 2:
                green = chroma;
                blue = secondary;
                break;
            case 3:
                green = secondary;
                blue = chroma;
                break;
            case 4:
                red = secondary;
                blue = chroma;
                break;
            case 5:
            case 6:
                red = chroma;
                blue = secondary;
                break;
        }

        return new RGB(red + match, green + match, blue + match);
    }

    public ARGB toARGB(float alpha)
    {
        return toRGB().toARGB(alpha);
    }
}
