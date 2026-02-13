package dev.obscuria.fragmentum.content.world.tooltip;

import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

@SuppressWarnings("unused")
public class TooltipTags {

    public static void register(String key, Instance instance) {
        throw new NotImplementedException();
    }

    public static void open(TooltipBuilder builder, String key, List<String> args) {
        throw new NotImplementedException();
    }

    public static void close(TooltipBuilder builder, String key) {
        throw new NotImplementedException();
    }

    public interface Instance {

        void open(TooltipBuilder builder, List<String> args);

        void close(TooltipBuilder builder);
    }
}