package dev.obscuria.fragmentum.util.color;

import com.mojang.serialization.Codec;
import lombok.With;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

@SuppressWarnings("ALL")
public record RGB(
        @With float red,
        @With float green,
        @With float blue)
{
    public static final Codec<RGB> DECIMAL_CODEC = null;
    public static final Codec<RGB> HEXADECIMAL_CODEC = null;
    public static final Codec<RGB> NORMALIZED_CODEC = null;
    public static final Codec<RGB> CODEC = null;

    public int decimal()
    {
        throw new NotImplementedException();
    }

    public String hexadecimal()
    {
        throw new NotImplementedException();
    }

    public List<Float> normalized()
    {
        throw new NotImplementedException();
    }

    public RGB lerp(RGB to, float delta)
    {
        throw new NotImplementedException();
    }

    public ARGB toARGB(float alpha)
    {
        throw new NotImplementedException();
    }

    public HSV toHSV()
    {
        throw new NotImplementedException();
    }
}
