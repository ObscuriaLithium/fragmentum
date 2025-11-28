package dev.obscuria.fragmentum.content.world.tooltip;

import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

@SuppressWarnings("unused")
public final class TooltipTemplates {

    public static void register(String key, Processor processor) {
        throw new NotImplementedException();
    }

    public static String process(Object source, String key, List<String> args) {
        throw new NotImplementedException();
    }

    @FunctionalInterface
    public interface Processor {

        String process(Object source, List<String> args);
    }
}