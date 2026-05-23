package dev.obscuria.fragmentum.v2.core.common;

import dev.obscuria.fragmentum.v2.api.common.Color;

final class _PackedColor implements Color {

    private final int packed;

    private float alpha = Float.NaN;
    private float red = Float.NaN;
    private float green = Float.NaN;
    private float blue = Float.NaN;

    private float hue = Float.NaN;
    private float saturation = Float.NaN;
    private float value = Float.NaN;

    _PackedColor(int argb) {
        this.packed = argb;
    }

    @Override public int argb() {
        return packed;
    }

    @Override public float alpha() {
        if (Float.isNaN(alpha)) alpha = _Color.byteToFloat(packed >>> 24);
        return alpha;
    }

    @Override public float red() {
        if (Float.isNaN(red)) red = _Color.byteToFloat(packed >>> 16);
        return red;
    }

    @Override public float green() {
        if (Float.isNaN(green)) green = _Color.byteToFloat(packed >>> 8);
        return green;
    }

    @Override public float blue() {
        if (Float.isNaN(blue)) blue = _Color.byteToFloat(packed);
        return blue;
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
        return new _PackedColor((_Color.floatToByte(a) << 24) | (packed & 0x00FFFFFF));
    }

    @Override public Color withRed(float r) {
        return new _PackedColor((packed & 0xFF00FFFF) | (_Color.floatToByte(r) << 16));
    }

    @Override public Color withGreen(float g) {
        return new _PackedColor((packed & 0xFFFF00FF) | (_Color.floatToByte(g) << 8));
    }

    @Override public Color withBlue(float b) {
        return new _PackedColor((packed & 0xFFFFFF00) | _Color.floatToByte(b));
    }

    @Override public Color withHue(float h) {
        return _Color.ofHSV(alpha(), h, saturation(), value());
    }

    @Override public Color withSaturation(float s) {
        return _Color.ofHSV(alpha(), hue(), s, value());
    }

    @Override public Color withValue(float v) {
        return _Color.ofHSV(alpha(), hue(), saturation(), v);
    }

    @Override public Color lerp(Color to, float delta) {
        float inv = 1f - delta;
        return _Color.ofComponents(alpha() * inv + to.alpha() * delta, red() * inv + to.red() * delta, green() * inv + to.green() * delta, blue() * inv + to.blue() * delta);
    }

    @Override public Color asPacked() {
        return this;
    }

    @Override public Color asComponents() {
        return _Color.ofComponents(alpha(), red(), green(), blue());
    }

    @Override public Color asHSV() {
        return _Color.ofHSV(alpha(), hue(), saturation(), value());
    }

    @Override public boolean equals(Object o) {
        return o instanceof Color c && c.argb() == packed;
    }

    @Override public int hashCode() {return packed;}

    @Override public String toString() {
        return "Color[#" + toHex() + "]";
    }

    private void ensureHSV() {
        if (!Float.isNaN(hue)) return;
        float[] hsv = _Color.rgbToHSV(red(), green(), blue());
        hue = hsv[0];
        saturation = hsv[1];
        value = hsv[2];
    }
}
