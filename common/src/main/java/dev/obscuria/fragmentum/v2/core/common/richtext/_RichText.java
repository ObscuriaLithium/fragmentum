package dev.obscuria.fragmentum.v2.core.common.richtext;

import dev.obscuria.fragmentum.v2.api.common.richtext.RichTextOptions;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class _RichText {

    private _RichText() {}

    public static List<Component> process(
            String input,
            @Nullable Object source,
            @Nullable RichTextOptions optionsOrNull
    ) {
        var options = optionsOrNull != null ? optionsOrNull : RichTextOptions.DEFAULT;
        var tokens  = _RichTextLexer.tokenize(input, source);
        var builder = new _RichTextBuilder(options);
        _RichTextLayout.layout(tokens, builder);
        return builder.build();
    }
}
