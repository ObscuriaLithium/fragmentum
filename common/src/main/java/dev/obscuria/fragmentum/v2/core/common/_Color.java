package dev.obscuria.fragmentum.v2.core.common;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.obscuria.fragmentum.v2.api.common.Color;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class _Color {

    public static final Codec<Color> DECIMAL_CODEC;
    public static final Codec<Color> HEX_CODEC;
    public static final Codec<Color> NORMALIZED_CODEC;
    public static final Codec<Color> CODEC;

    public static Color ofPacked(int argb) {
        return new _PackedColor(argb);
    }

    public static Color ofPacked(int a, int r, int g, int b) {
        return new _PackedColor(((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF));
    }

    public static Color ofComponents(float alpha, float red, float green, float blue) {
        return new _ComponentColor(alpha, red, green, blue);
    }

    public static Color ofHSV(float alpha, float hue, float saturation, float value) {
        return new _HSVColor(alpha, hue, saturation, value);
    }

    public static Color ofHex(String hex) {
        String s = hex.startsWith("#") ? hex.substring(1) : hex;
        return switch (s.length()) {
            case 6 -> ofPacked(0xFF000000 | (int) Long.parseLong(s, 16));
            case 8 -> ofPacked((int) Long.parseLong(s, 16));
            default -> throw new IllegalArgumentException("Invalid hex color: " + hex);
        };
    }

    static int hsvToRgbInt(float h, float s, float v) {
        if (s == 0f) {
            int c = Math.round(v * 255);
            return (c << 16) | (c << 8) | c;
        }
        float hh = (h % 360f) / 60f;
        int i = (int) hh;
        float f = hh - i;
        float p = v * (1f - s);
        float q = v * (1f - s * f);
        float t = v * (1f - s * (1f - f));
        float r, g, b;
        switch (i) {
            case 0 -> {
                r = v;
                g = t;
                b = p;
            }
            case 1 -> {
                r = q;
                g = v;
                b = p;
            }
            case 2 -> {
                r = p;
                g = v;
                b = t;
            }
            case 3 -> {
                r = p;
                g = q;
                b = v;
            }
            case 4 -> {
                r = t;
                g = p;
                b = v;
            }
            default -> {
                r = v;
                g = p;
                b = q;
            }
        }
        return (Math.round(r * 255) << 16) | (Math.round(g * 255) << 8) | Math.round(b * 255);
    }

    static float[] rgbToHSV(float r, float g, float b) {
        float max = Math.max(r, Math.max(g, b));
        float min = Math.min(r, Math.min(g, b));
        float delta = max - min;
        float h, s, v = max;
        if (delta < 1e-6f) return new float[]{0f, 0f, v};
        s = delta / max;
        if (r == max) h = 60f * (((g - b) / delta) % 6f);
        else if (g == max) h = 60f * (((b - r) / delta) + 2f);
        else h = 60f * (((r - g) / delta) + 4f);
        if (h < 0f) h += 360f;
        return new float[]{h, s, v};
    }

    static float clamp01(float v) {
        return v < 0f ? 0f : Math.min(v, 1f);
    }

    static float byteToFloat(int b) {
        return (b & 0xFF) / 255f;
    }

    static int floatToByte(float f) {
        return Math.round(clamp01(f) * 255);
    }

    static {
        DECIMAL_CODEC = Codec.INT.xmap(_Color::ofPacked, Color::argb);
        HEX_CODEC = Codec.STRING.xmap(_Color::ofHex, Color::toHex);
        NORMALIZED_CODEC = RecordCodecBuilder.create(inst -> inst.group(Codec.FLOAT.fieldOf("a").forGetter(Color::alpha), Codec.FLOAT.fieldOf("r").forGetter(Color::red), Codec.FLOAT.fieldOf("g").forGetter(Color::green), Codec.FLOAT.fieldOf("b").forGetter(Color::blue)).apply(inst, _Color::ofComponents));
        CODEC = DECIMAL_CODEC.withAlternative(HEX_CODEC).withAlternative(NORMALIZED_CODEC);
    }
}
