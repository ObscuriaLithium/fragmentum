package dev.obscuria.fragmentum.util.color;

import com.mojang.serialization.Codec;
import lombok.With;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

@SuppressWarnings("ALL")
public record ARGB(
        @With float alpha,
        @With float red,
        @With float green,
        @With float blue)
{
    public static final Codec<ARGB> DECIMAL_CODEC = null;
    public static final Codec<ARGB> HEXADECIMAL_CODEC = null;
    public static final Codec<ARGB> NORMALIZED_CODEC = null;
    public static final Codec<ARGB> CODEC = null;

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

    public ARGB lerp(ARGB to, float delta)
    {
        throw new NotImplementedException();
    }

    public RGB toRGB()
    {
        throw new NotImplementedException();
    }

    public HSV toHSV()
    {
        throw new NotImplementedException();
    }
}
