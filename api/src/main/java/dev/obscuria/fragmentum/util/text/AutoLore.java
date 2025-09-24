package dev.obscuria.fragmentum.util.text;

import net.minecraft.ChatFormatting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings("unused")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoLore
{
    ChatFormatting style() default ChatFormatting.GRAY;
}
