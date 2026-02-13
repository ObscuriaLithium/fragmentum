package dev.obscuria.fragmentum.content.world.tooltip;

import dev.obscuria.fragmentum.content.util.color.Colors;
import dev.obscuria.fragmentum.content.util.color.RGB;
import net.minecraft.ChatFormatting;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TooltipTags {

    private static final Map<String, Instance> REGISTRY = new HashMap<>();
    private static final long START_TIME = System.currentTimeMillis();

    static {
        register("color", Color.INSTANCE);
        register("chroma", Chroma.INSTANCE);
        register("br", LineBreak.INSTANCE);
        register("i", new SimpleFlag(StyleFlag.ITALIC));
        register("b", new SimpleFlag(StyleFlag.BOLD));
        register("no-i", new SimpleFlag(StyleFlag.NON_ITALIC));
        register("no-b", new SimpleFlag(StyleFlag.NON_BOLD));
    }

    public static void register(String key, Instance instance) {
        REGISTRY.putIfAbsent(key, instance);
    }

    public static void open(TooltipBuilder builder, String key, List<String> args) {
        final var instance = REGISTRY.get(key);
        if (instance != null) instance.open(builder, args);
    }

    public static void close(TooltipBuilder builder, String key) {
        final var instance = REGISTRY.get(key);
        if (instance != null) instance.close(builder);
    }

    public interface Instance {

        void open(TooltipBuilder builder, List<String> args);

        void close(TooltipBuilder builder);
    }

    private record SimpleFlag(StyleFlag flag) implements Instance {

        @Override
        public void open(TooltipBuilder builder, List<String> args) {
            builder.pushFlag(flag);
        }

        @Override
        public void close(TooltipBuilder builder) {
            builder.popFlag(flag);
        }
    }

    private enum LineBreak implements Instance {
        INSTANCE;

        @Override
        public void open(TooltipBuilder builder, List<String> args) {
            builder.breakLine();
        }

        @Override
        public void close(TooltipBuilder builder) {
            builder.breakLine();
        }
    }

    private enum Color implements Instance {
        INSTANCE;

        @Override
        public void open(TooltipBuilder builder, List<String> args) {
            try {
                if (args.isEmpty()) return;
                final var arg = args.get(0);
                if (arg.startsWith("#")) {
                    builder.pushColor(Colors.rgbOf(arg));
                } else {
                    final @Nullable var color = Objects.requireNonNull(ChatFormatting.getByName(arg)).getColor();
                    builder.pushColor(Colors.rgbOf(color == null ? 0xffffff : color));
                }
            } catch (Exception ignored) {}
        }

        @Override
        public void close(TooltipBuilder builder) {
            builder.popColor();
        }
    }

    private enum Chroma implements Instance {
        INSTANCE;

        @Override
        public void open(TooltipBuilder builder, List<String> args) {
            try {
                if (args.size() == 1) {
                    final @Nullable var preset = ChromaPresets.get(args.get(0));
                    if (preset == null) return;
                    apply(builder, preset.first(), preset.second(), preset.speed());
                } else if (args.size() >= 2) {
                    final var first = Colors.rgbOf(args.get(0));
                    final var second = Colors.rgbOf(args.get(1));
                    float speed = args.size() >= 3 ? Float.parseFloat(args.get(2)) : 1f;
                    apply(builder, first, second, speed);
                }
            } catch (Exception ignored) {}
        }

        @Override
        public void close(TooltipBuilder builder) {
            builder.popColor();
        }

        private void apply(TooltipBuilder builder, RGB first, RGB second, float speed) {
            float seconds = (System.currentTimeMillis() - START_TIME) / 1000f;
            builder.pushColor(first.lerp(second, 0.5f + 0.5f * (float) Math.sin(seconds * speed)));
        }
    }
}