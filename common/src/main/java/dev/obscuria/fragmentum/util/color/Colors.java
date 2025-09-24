package dev.obscuria.fragmentum.util.color;

import java.util.List;

public interface Colors
{
    static ARGB argbOf(float alpha, float red, float green, float blue)
    {
        return new ARGB(alpha, red, green, blue);
    }

    static ARGB argbOf(int decimal)
    {
        return new ARGB(
                ((decimal >> 24) & 0xFF) / 255f,
                ((decimal >> 16) & 0xFF) / 255f,
                ((decimal >> 8) & 0xFF) / 255f,
                (decimal & 0xFF) / 255f);
    }

    static ARGB argbOf(String hexadecimal)
    {
        final var hex = hexadecimal.replace("#", "");
        return argbOf((int) Long.parseLong(hex, 16));
    }

    static ARGB argbOf(List<Float> normalized)
    {
        if (normalized.size() != 4) throw new IllegalArgumentException();
        return argbOf(normalized.get(0), normalized.get(1), normalized.get(2), normalized.get(3));
    }

    static RGB rgbOf(float red, float green, float blue)
    {
        return new RGB(red, green, blue);
    }

    static RGB rgbOf(int decimal)
    {
        return new RGB(
                ((decimal >> 16) & 0xFF) / 255f,
                ((decimal >> 8) & 0xFF) / 255f,
                (decimal & 0xFF) / 255f);
    }

    static RGB rgbOf(String hexadecimal)
    {
        final var hex = hexadecimal.replace("#", "");
        return rgbOf(Integer.parseInt(hex, 16) | (0xFF << 24));
    }

    static RGB rgbOf(List<Float> normalized)
    {
        if (normalized.size() != 3) throw new IllegalArgumentException();
        return rgbOf(normalized.get(0), normalized.get(1), normalized.get(2));
    }

    static HSV hsvOf(float hue, float saturation, float value)
    {
        return new HSV(hue, saturation, value);
    }

    static HSV hsvOf(List<Float> normalized)
    {
        if (normalized.size() != 3) throw new IllegalArgumentException();
        return hsvOf(normalized.get(0), normalized.get(1), normalized.get(2));
    }
}
