package dev.obscuria.fragmentum.v2.api.common;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.v2.core.common._Color;

@SuppressWarnings("unused")
public interface Color {

    Color WHITE = packed(0xFFFFFFFF);
    Color BLACK = packed(0xFF000000);
    Color TRANSPARENT = packed(0x00000000);
    Color CLEAR = packed(0x00FFFFFF);
    Color RED = packed(0xFFFF0000);
    Color GREEN = packed(0xFF00FF00);
    Color BLUE = packed(0xFF0000FF);
    Color YELLOW = packed(0xFFFFFF00);
    Color CYAN = packed(0xFF00FFFF);
    Color MAGENTA = packed(0xFFFF00FF);

    static Codec<Color> codec() {
        return _Color.CODEC;
    }

    static Codec<Color> decimalCodec() {
        return _Color.DECIMAL_CODEC;
    }

    static Codec<Color> hexCodex() {
        return _Color.HEX_CODEC;
    }

    static Codec<Color> normalizedCodec() {
        return _Color.NORMALIZED_CODEC;
    }

    static Color packed(int argb) {
        return _Color.ofPacked(argb);
    }

    static Color packed(int a, int r, int g, int b) {
        return _Color.ofPacked(a, r, g, b);
    }

    static Color packedRGB(int rgb) {
        return _Color.ofPacked(0xFF000000 | (rgb & 0x00FFFFFF));
    }

    static Color components(float alpha, float red, float green, float blue) {
        return _Color.ofComponents(alpha, red, green, blue);
    }

    static Color components(float red, float green, float blue) {
        return _Color.ofComponents(1f, red, green, blue);
    }

    static Color hsv(float alpha, float hue, float saturation, float value) {
        return _Color.ofHSV(alpha, hue, saturation, value);
    }

    static Color hsv(float hue, float saturation, float value) {
        return _Color.ofHSV(1f, hue, saturation, value);
    }

    static Color parse(String hex) {
        return _Color.ofHex(hex);
    }

    static Color parse(int argb) {
        return _Color.ofPacked(argb);
    }

    float alpha();

    float red();

    float green();

    float blue();

    int argb();

    default int rgb() {
        return argb() & 0x00FFFFFF;
    }

    default int alphaInt() {
        return (argb() >>> 24) & 0xFF;
    }

    default int redInt() {
        return (argb() >>> 16) & 0xFF;
    }

    default int greenInt() {
        return (argb() >>> 8) & 0xFF;
    }

    default int blueInt() {
        return argb() & 0xFF;
    }

    float hue();

    float saturation();

    float value();

    default String toHex() {
        return String.format("%08X", argb());
    }

    default String toHexRGB() {
        return String.format("%06X", rgb());
    }

    Color withAlpha(float alpha);

    default Color withAlpha(int alpha) {
        return withAlpha(alpha / 255f);
    }

    Color withRed(float red);

    Color withGreen(float green);

    Color withBlue(float blue);

    Color withHue(float hue);

    Color withSaturation(float saturation);

    Color withValue(float value);

    default Color shiftHue(float degrees) {
        return withHue((hue() + degrees) % 360f);
    }

    default Color shiftSaturation(float delta) {
        return withSaturation(Math.clamp(saturation() + delta, 0f, 1f));
    }

    default Color shiftValue(float delta) {
        return withValue(Math.clamp(value() + delta, 0f, 1f));
    }

    Color lerp(Color to, float delta);

    default Color multiply(float factor) {
        return components(alpha(),
                red() * factor,
                green() * factor,
                blue() * factor);
    }

    default Color over(Color overlay) {
        float sa = overlay.alpha();
        float da = alpha() * (1f - sa);
        float out = sa + da;
        if (out == 0f) return Color.TRANSPARENT;
        return components(
                out,
                (overlay.red() * sa + red() * da) / out,
                (overlay.green() * sa + green() * da) / out,
                (overlay.blue() * sa + blue() * da) / out
        );
    }

    default void intoRGBA(FloatQuadConsumer consumer) {
        consumer.accept(red(), green(), blue(), alpha());
    }

    default void intoARGB(FloatQuadConsumer consumer) {
        consumer.accept(alpha(), red(), green(), blue());
    }

    Color asPacked();

    Color asComponents();

    Color asHSV();

    @FunctionalInterface
    interface FloatQuadConsumer {

        void accept(float a, float b, float c, float d);
    }
}
