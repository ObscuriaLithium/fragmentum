package dev.obscuria.fragmentum.api.v1.common.text;

import dev.obscuria.fragmentum.api.v1.common.V1Common;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import java.util.List;
import java.util.function.Function;

/**
 * A utility interface for creating and manipulating text components with various styles and formatting options.
 * The `TextWrapper` interface provides a flexible way to handle multi-line text,
 * apply styles, prefixes, and custom formatting functions to components, and then
 * generate the resulting text components as a list.
 */
@SuppressWarnings("unused")
public interface TextWrapper
{
    /**
     * Creates a new `TextWrapper` based on the provided raw string text.
     *
     * @param text The raw string text to wrap.
     * @return A {@link TextWrapper} instance for further manipulation.
     */
    static TextWrapper create(String text)
    {
        return V1Common.INSTANCE.newTextWrapper(text);
    }

    /**
     * Creates a new `TextWrapper` based on the provided {@link Component}.
     *
     * @param component The {@link Component} to wrap.
     * @return A {@link TextWrapper} instance for further manipulation.
     */
    static TextWrapper create(Component component)
    {
        return V1Common.INSTANCE.newTextWrapper(component);
    }

    /**
     * Sets the maximum line length for the wrapped text.
     * <p>
     * This allows the text to automatically wrap to a new line if it exceeds the given maximum length.
     *
     * @param length The maximum length of a single line.
     * @return This {@link TextWrapper} instance for chaining further modifications.
     */
    TextWrapper withMaxLength(int length);

    /**
     * Adds a prefix to all lines in the wrapped text.
     *
     * @param prefix The prefix to add to each line of the text.
     * @return This {@link TextWrapper} instance for chaining further modifications.
     */
    TextWrapper withPrefix(String prefix);

    /**
     * Adds a prefix to a specific line in the wrapped text.
     *
     * @param index  The line index (0-based) to which the prefix should be applied.
     * @param prefix The prefix to add to the specified line.
     * @return This {@link TextWrapper} instance for chaining further modifications.
     */
    TextWrapper withLinePrefix(int index, String prefix);

    /**
     * Applies a {@link Style} to all lines in the wrapped text.
     *
     * @param style The {@link Style} to apply to the text.
     * @return This {@link TextWrapper} instance for chaining further modifications.
     */
    TextWrapper withStyle(Style style);

    /**
     * Applies a {@link Style} to a specific line in the wrapped text.
     *
     * @param index The line index (0-based) to which the style should be applied.
     * @param style The {@link Style} to apply to the specified line.
     * @return This {@link TextWrapper} instance for chaining further modifications.
     */
    TextWrapper withLineStyle(int index, Style style);

    /**
     * Applies a custom transformation function to the text fragments.
     * <p>
     * This allows for advanced customization of how the text is turned into {@link Component}s.
     *
     * @param function A {@link Function} that takes a {@link String} and produces a {@link Component}.
     * @return This {@link TextWrapper} instance for chaining further modifications.
     */
    TextWrapper fragment(Function<String, Component> function);

    /**
     * Builds the wrapped text as a list of {@link Component}s with the applied styles and formatting.
     *
     * @return A {@link List} of {@link Component}s representing the fully constructed text.
     */
    List<? extends Component> build();
}