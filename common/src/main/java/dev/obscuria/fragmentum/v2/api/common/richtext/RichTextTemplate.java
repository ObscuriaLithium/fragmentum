package dev.obscuria.fragmentum.v2.api.common.richtext;

import dev.obscuria.fragmentum.v2.core.common.richtext._RichTextTemplates;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@UtilityClass
@SuppressWarnings("unused")
public final class RichTextTemplate {

    public static void register(String key, Processor processor) {
        _RichTextTemplates.register(key, processor);
    }

    public static String evaluate(@Nullable Object source, String key, List<String> args) {
        return _RichTextTemplates.evaluate(source, key, args);
    }

    @FunctionalInterface
    public interface Processor {

        @Nullable String process(@Nullable Object source, List<String> args);
    }
}
