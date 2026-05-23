package dev.obscuria.fragmentum.v2.core.common;

import dev.obscuria.fragmentum.v2.api.common.Color;

final class _HSVColor implements Color {

    private final float alpha;
    private final float hue;        // [0, 360)
    private final float saturation; // [0, 1]
    private final float value;      // [0, 1]

    private float red = Float.NaN;
    private float green = Float.NaN;
    private float blue = Float.NaN;

    private int packed;
    private boolean packedReady = false;

    _HSVColor(float alpha, float hue, float saturation, float value) {
        this.alpha = _Color.clamp01(alpha);
        this.hue = ((hue % 360f) + 360f) % 360f;
        this.saturation = _Color.clamp01(saturation);
        this.value = _Color.clamp01(value);
    }

    @Override public float hue() {
        return hue;
    }

    @Override public float saturation() {
        return saturation;
    }

    @Override public float value() {
        return value;
    }

    @Override public float alpha() {
        return alpha;
    }

    @Override public float red() {
        ensureRGB();
        return red;
    }

    @Override public float green() {
        ensureRGB();
        return green;
    }

    @Override public float blue() {
        ensureRGB();
        return blue;
    }

    @Override public int argb() {
        if (!packedReady) {
            int rgb = _Color.hsvToRgbInt(hue, saturation, value);
            packed = (_Color.floatToByte(alpha) << 24) | rgb;
            packedReady = true;
        }
        return packed;
    }

    @Override public Color withAlpha(float a) {
        return _Color.ofHSV(a, hue, saturation, value);
    }

    @Override public Color withHue(float h) {
        return _Color.ofHSV(alpha, h, saturation, value);
    }

    @Override public Color withSaturation(float s) {
        return _Color.ofHSV(alpha, hue, s, value);
    }

    @Override public Color withValue(float v) {
        return _Color.ofHSV(alpha, hue, saturation, v);
    }

    @Override public Color withRed(float r) {
        return _Color.ofComponents(alpha, r, green(), blue());
    }

    @Override public Color withGreen(float g) {
        return _Color.ofComponents(alpha, red(), g, blue());
    }

    @Override public Color withBlue(float b) {
        return _Color.ofComponents(alpha, red(), green(), b);
    }

    @Override public Color lerp(Color to, float delta) {
        float inv = 1f - delta;
        return _Color.ofComponents(alpha * inv + to.alpha() * delta, red() * inv + to.red() * delta, green() * inv + to.green() * delta, blue() * inv + to.blue() * delta);
    }

    @Override public Color asPacked() {
        return _Color.ofPacked(argb());
    }

    @Override public Color asComponents() {
        return _Color.ofComponents(alpha, red(), green(), blue());
    }

    @Override public Color asHSV() {
        return this;
    }

    @Override public boolean equals(Object o) {
        return o instanceof Color c && c.argb() == argb();
    }

    @Override public int hashCode() {
        return argb();
    }

    @Override public String toString() {
        return "Color[#" + toHex() + " hsv(" + hue + "°," + saturation + "," + value + ")]";
    }

    private void ensureRGB() {
        if (!Float.isNaN(red)) return;
        int rgb = _Color.hsvToRgbInt(hue, saturation, value);
        red = _Color.byteToFloat(rgb >>> 16);
        green = _Color.byteToFloat(rgb >>> 8);
        blue = _Color.byteToFloat(rgb);
    }
}
