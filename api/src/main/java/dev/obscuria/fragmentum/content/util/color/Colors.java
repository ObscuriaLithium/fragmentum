package dev.obscuria.fragmentum.content.util.color;

import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

@SuppressWarnings("unused")
public interface Colors
{
    static ARGB argbOf(float alpha, float red, float green, float blue)
    {
        throw new NotImplementedException();
    }

    static ARGB argbOf(int decimal)
    {
        throw new NotImplementedException();
    }

    static ARGB argbOf(String hexadecimal)
    {
        throw new NotImplementedException();
    }

    static ARGB argbOf(List<Float> normalized)
    {
        throw new NotImplementedException();
    }

    static RGB rgbOf(float red, float green, float blue)
    {
        throw new NotImplementedException();
    }

    static RGB rgbOf(int decimal)
    {
        throw new NotImplementedException();
    }

    static RGB rgbOf(String hexadecimal)
    {
        throw new NotImplementedException();
    }

    static RGB rgbOf(List<Float> normalized)
    {
        throw new NotImplementedException();
    }

    static HSV hsvOf(float hue, float saturation, float value)
    {
        throw new NotImplementedException();
    }

    static HSV hsvOf(List<Float> normalized)
    {
        throw new NotImplementedException();
    }
}
