package dev.obscuria.fragmentum.v2.core.common.richtext;

import dev.obscuria.fragmentum.Fragmentum;
import dev.obscuria.fragmentum.v2.api.common.richtext.RichTextTemplate;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class _RichTextTemplates {

    private static final String MISSING_TEMPLATE_SYMBOL = "🗙";

    private static final Map<String, RichTextTemplate.Processor> REGISTRY = new HashMap<>();

    private _RichTextTemplates() {}

    public static void register(String key, RichTextTemplate.Processor processor) {
        if (REGISTRY.putIfAbsent(key, processor) != null) {
            Fragmentum.LOG.warn("Template '{}' is already registered; ignoring duplicate.", key);
        }
    }

    public static String evaluate(@Nullable Object source, String key, List<String> args) {
        var processor = REGISTRY.get(key);
        if (processor == null) {
            Fragmentum.LOG.warn("No template registered for key '{}'", key);
            return MISSING_TEMPLATE_SYMBOL;
        }
        try {
            @Nullable var result = processor.process(source, args);
            return result != null ? result : "";
        } catch (Exception e) {
            Fragmentum.LOG.error("Template '{}' threw an exception", key, e);
            return MISSING_TEMPLATE_SYMBOL;
        }
    }
}
