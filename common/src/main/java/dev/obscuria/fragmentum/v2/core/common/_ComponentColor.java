package dev.obscuria.fragmentum.v2.core.common;

import dev.obscuria.fragmentum.v2.api.common.Color;

final class _ComponentColor implements Color {

    private final float alpha;
    private final float red;
    private final float green;
    private final float blue;

    private int packed = Integer.MIN_VALUE;
    private boolean packedReady = false;

    private float hue = Float.NaN;
    private float saturation = Float.NaN;
    private float value = Float.NaN;

    _ComponentColor(float alpha, float red, float green, float blue) {
        this.alpha = _Color.clamp01(alpha);
        this.red = _Color.clamp01(red);
        this.green = _Color.clamp01(green);
        this.blue = _Color.clamp01(blue);
    }

    @Override public float alpha() {
        return alpha;
    }

    @Override public float red() {
        return red;
    }

    @Override public float green() {
        return green;
    }

    @Override public float blue() {
        return blue;
    }

    @Override public int argb() {
        if (!packedReady) {
            packed = (_Color.floatToByte(alpha) << 24) | (_Color.floatToByte(red) << 16) | (_Color.floatToByte(green) << 8) | _Color.floatToByte(blue);
            packedReady = true;
        }
        return packed;
    }

    @Override public float hue() {
        ensureHSV();
        return hue;
    }

    @Override public float saturation() {
        ensureHSV();
        return saturation;
    }

    @Override public float value() {
        ensureHSV();
        return value;
    }

    @Override public Color withAlpha(float a) {
        return _Color.ofComponents(a, red, green, blue);
    }

    @Override public Color withRed(float r) {
        return _Color.ofComponents(alpha, r, green, blue);
    }

    @Override public Color withGreen(float g) {
        return _Color.ofComponents(alpha, red, g, blue);
    }

    @Override public Color withBlue(float b) {
        return _Color.ofComponents(alpha, red, green, b);
    }

    @Override public Color withHue(float h) {
        return _Color.ofHSV(alpha, h, saturation(), value());
    }

    @Override public Color withSaturation(float s) {
        return _Color.ofHSV(alpha, hue(), s, value());
    }

    @Override public Color withValue(float v) {
        return _Color.ofHSV(alpha, hue(), saturation(), v);
    }

    @Override public Color lerp(Color to, float delta) {
        float inv = 1f - delta;
        return _Color.ofComponents(alpha * inv + to.alpha() * delta, red * inv + to.red() * delta, green * inv + to.green() * delta, blue * inv + to.blue() * delta);
    }

    @Override public Color asPacked() {
        return _Color.ofPacked(argb());
    }

    @Override public Color asComponents() {
        return this;
    }

    @Override public Color asHSV() {
        return _Color.ofHSV(alpha, hue(), saturation(), value());
    }

    @Override public boolean equals(Object o) {
        return o instanceof Color c && c.argb() == argb();
    }

    @Override public int hashCode() {
        return argb();
    }

    @Override public String toString() {
        return "Color[#" + toHex() + "]";
    }

    private void ensureHSV() {
        if (!Float.isNaN(hue)) return;
        float[] hsv = _Color.rgbToHSV(red, green, blue);
        hue = hsv[0];
        saturation = hsv[1];
        value = hsv[2];
    }
}
