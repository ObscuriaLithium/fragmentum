package dev.obscuria.fragmentum.util.color;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.extension.CodecExtension;
import lombok.With;
import net.minecraft.util.Mth;

import java.util.List;

@SuppressWarnings("unused")
public record RGB(
        @With float red,
        @With float green,
        @With float blue)
{
    public static final Codec<RGB> DECIMAL_CODEC = Codec.INT.xmap(Colors::rgbOf, RGB::decimal);
    public static final Codec<RGB> HEXADECIMAL_CODEC = Codec.STRING.xmap(Colors::rgbOf, RGB::hexadecimal);
    public static final Codec<RGB> NORMALIZED_CODEC = Codec.FLOAT.listOf().xmap(Colors::rgbOf, RGB::normalized);
    public static final Codec<RGB> CODEC =CodecExtension.withAlternative(DECIMAL_CODEC, HEXADECIMAL_CODEC, NORMALIZED_CODEC);

    public int decimal()
    {
        final var r = Math.min(255, Math.max(0, (int) (red * 255)));
        final var g = Math.min(255, Math.max(0, (int) (green * 255)));
        final var b = Math.min(255, Math.max(0, (int) (blue * 255)));
        return (r << 16) | (g << 8) | b;
    }

    public String hexadecimal()
    {
        return String.format("#%06X", decimal() & 0xFFFFFF);
    }

    public List<Float> normalized()
    {
        return List.of(red, green, blue);
    }

    public RGB lerp(RGB to, float delta)
    {
        return new RGB(
                Mth.lerp(delta, red, to.red),
                Mth.lerp(delta, green, to.green),
                Mth.lerp(delta, blue, to.blue));
    }

    public ARGB toARGB(float alpha)
    {
        return new ARGB(alpha, red, green, blue);
    }

    public HSV toHSV() {

        final var max = Math.max(red, Math.max(green, blue));
        final var min = Math.min(red, Math.min(green, blue));
        final var delta = max - min;

        float hue;
        if (delta == 0f) {
            hue = 0f;
        } else if (max == red) {
            hue = (green - blue) / delta;
        } else if (max == green) {
            hue = (blue - red) / delta + 2f;
        } else {
            hue = (red - green) / delta + 4f;
        }
        hue = (hue / 6f) % 1f;
        if (hue < 0f) hue += 1f;

        float saturation = max == 0f ? 0f : delta / max;

        return new HSV(hue, saturation, max);
    }
}
