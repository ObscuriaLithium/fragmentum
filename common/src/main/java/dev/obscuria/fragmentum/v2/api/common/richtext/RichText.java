package dev.obscuria.fragmentum.v2.api.common.richtext;

import dev.obscuria.fragmentum.v2.core.common.richtext._RichText;
import lombok.experimental.UtilityClass;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@UtilityClass
@SuppressWarnings("unused")
public final class RichText {

    public static List<Component> process(Component input) {
        return _RichText.process(input.getString(), null, RichTextOptions.DEFAULT);
    }

    public static List<Component> process(Component input, RichTextOptions options) {
        return _RichText.process(input.getString(), null, options);
    }

    public static List<Component> process(Component input, @Nullable Object source) {
        return _RichText.process(input.getString(), source, RichTextOptions.DEFAULT);
    }

    public static List<Component> process(Component input, @Nullable Object source, RichTextOptions options) {
        return _RichText.process(input.getString(), source, options);
    }

    public static List<Component> process(String input, @Nullable Object source, RichTextOptions options) {
        return _RichText.process(input, source, options);
    }

    public static List<Component> process(String input) {
        return _RichText.process(input, null, RichTextOptions.DEFAULT);
    }
}
