package dev.obscuria.fragmentum.v2.api.common.richtext;

import dev.obscuria.fragmentum.v2.core.common.richtext._RichTextTags;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
@SuppressWarnings("unused")
public final class RichTextTag {

    public static void register(String key, Handler handler) {
        _RichTextTags.register(key, handler);
    }

    public interface Handler {

        void open(RichTextContext ctx, List<String> args);

        void close(RichTextContext ctx);
    }

    @FunctionalInterface
    public interface SelfClosing extends Handler {

        void execute(RichTextContext ctx, List<String> args);

        @Override
        default void open(RichTextContext ctx, List<String> args) {
            execute(ctx, args);
        }

        @Override
        default void close(RichTextContext ctx) {}
    }
}
