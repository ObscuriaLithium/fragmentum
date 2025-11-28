package dev.obscuria.fragmentum.content.util.color;

import com.mojang.serialization.Codec;
import lombok.With;
import net.minecraft.util.Mth;

import java.util.List;

@SuppressWarnings("unused")
public record ARGB(
        @With float alpha,
        @With float red,
        @With float green,
        @With float blue) {

    public static final Codec<ARGB> DECIMAL_CODEC = Codec.INT.xmap(Colors::argbOf, ARGB::decimal);
    public static final Codec<ARGB> HEXADECIMAL_CODEC = Codec.STRING.xmap(Colors::argbOf, ARGB::hexadecimal);
    public static final Codec<ARGB> NORMALIZED_CODEC = Codec.FLOAT.listOf().xmap(Colors::argbOf, ARGB::normalized);
    public static final Codec<ARGB> CODEC = Codec.withAlternative(DECIMAL_CODEC, Codec.withAlternative(HEXADECIMAL_CODEC, NORMALIZED_CODEC));

    public int decimal() {
        final var a = Math.min(255, Math.max(0, (int) (alpha * 255)));
        final var r = Math.min(255, Math.max(0, (int) (red * 255)));
        final var g = Math.min(255, Math.max(0, (int) (green * 255)));
        final var b = Math.min(255, Math.max(0, (int) (blue * 255)));
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public String hexadecimal() {
        return String.format("#%08X", decimal());
    }

    public List<Float> normalized() {
        return List.of(alpha, red, green, blue);
    }

    public ARGB lerp(ARGB to, float delta) {
        return new ARGB(
                Mth.lerp(delta, alpha, to.alpha),
                Mth.lerp(delta, red, to.red),
                Mth.lerp(delta, green, to.green),
                Mth.lerp(delta, blue, to.blue));
    }

    public RGB toRGB() {
        return new RGB(red, green, blue);
    }

    public HSV toHSV() {
        return toRGB().toHSV();
    }
}
