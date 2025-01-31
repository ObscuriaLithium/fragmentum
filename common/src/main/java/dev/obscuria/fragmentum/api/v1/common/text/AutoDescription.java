package dev.obscuria.fragmentum.api.v1.common.text;

import net.minecraft.ChatFormatting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to automatically generate descriptions (lore) for {@link net.minecraft.world.item.Item} subclasses.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoDescription
{
    /**
     * Defines the style of the generated description text.
     *
     * The {@link ChatFormatting} value specifies the desired color or text format
     * to be applied to the automatically generated description.
     *
     * @return The {@link ChatFormatting} style to use for the description.
     *         Default value is {@link ChatFormatting#GRAY}.
     */
    ChatFormatting style() default ChatFormatting.GRAY;
}
