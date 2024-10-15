package dev.obscuria.fragmentum.api.v1.common.text;

import dev.obscuria.fragmentum.api.v1.common.V1Common;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import java.util.List;
import java.util.function.Function;

@SuppressWarnings("unused")
public interface TextWrapper
{
    static TextWrapper create(String text)
    {
        return V1Common.INSTANCE.newTextWrapper(text);
    }

    static TextWrapper create(Component component)
    {
        return V1Common.INSTANCE.newTextWrapper(component);
    }

    TextWrapper withMaxLength(int length);

    TextWrapper withPrefix(String prefix);

    TextWrapper withLinePrefix(int index, String prefix);

    TextWrapper withStyle(Style style);

    TextWrapper withLineStyle(int index, Style style);

    TextWrapper fragment(Function<String, Component> function);

    List<? extends Component> build();
}
