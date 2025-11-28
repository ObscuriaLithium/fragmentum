package dev.obscuria.fragmentum.content.world.tooltip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TooltipTemplates {

    private static final Map<String, Processor> REGISTRY = new HashMap<>();

    public static void register(String key, Processor processor) {
        REGISTRY.putIfAbsent(key, processor);
    }

    public static String process(Object source, String key, List<String> args) {
        final var processor = REGISTRY.get(key);
        return processor == null ? "🗙" : processor.process(source, args);
    }

    @FunctionalInterface
    public interface Processor {

        String process(Object source, List<String> args);
    }
}