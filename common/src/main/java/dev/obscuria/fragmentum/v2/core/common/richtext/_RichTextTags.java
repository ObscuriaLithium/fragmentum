package dev.obscuria.fragmentum.v2.core.common.richtext;

import dev.obscuria.fragmentum.Fragmentum;
import dev.obscuria.fragmentum.v2.api.common.Color;
import dev.obscuria.fragmentum.v2.api.common.richtext.ChromaPresets;
import dev.obscuria.fragmentum.v2.api.common.richtext.RichTextContext;
import dev.obscuria.fragmentum.v2.api.common.richtext.RichTextTag;
import net.minecraft.ChatFormatting;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class _RichTextTags {

    private static final Map<String, RichTextTag.Handler> REGISTRY = new HashMap<>();
    private static final long START_TIME = System.currentTimeMillis();

    public static void register(String key, RichTextTag.Handler handler) {
        if (REGISTRY.putIfAbsent(key, handler) != null) {
            Fragmentum.LOG.warn("Tag '{}' is already registered; ignoring duplicate.", key);
        }
    }

    static void open(_RichTextBuilder builder, String key, List<String> args) {
        var handler = REGISTRY.get(key);
        if (handler != null) {
            try {
                handler.open(builder, args);
            } catch (Exception e) {
                Fragmentum.LOG.error("Tag '{}' open() threw an exception", key, e);
            }
        }
    }

    static void close(_RichTextBuilder builder, String key) {
        var handler = REGISTRY.get(key);
        if (handler != null) {
            try {
                handler.close(builder);
            } catch (Exception e) {
                Fragmentum.LOG.error("Tag '{}' close() threw an exception", key, e);
            }
        }
    }

    static {
        // [color=#RRGGBB] / [color=red]
        register("color", new ColorTag());
        // [chroma=preset] / [chroma=#from, #to, speed]
        register("chroma", new ChromaTag());
        // [br]
        register("br", (RichTextTag.SelfClosing) (ctx, args) -> ctx.breakLine());
        // [i] / [/i]
        register("i", stackFlag(true, false));
        // [b] / [/b]
        register("b", stackFlag(false, true));
        // [no-i] / [/no-i]
        register("no-i", stackFlag(false, false));
        // [no-b] / [/no-b]
        register("no-b", stackFlag(false, false));
    }

    private static RichTextTag.Handler stackFlag(boolean italic, boolean bold) {
        return new RichTextTag.Handler() {
            @Override
            public void open(RichTextContext ctx, List<String> args) {
                ctx.pushItalic(italic);
                ctx.pushBold(bold);
            }

            @Override public void close(RichTextContext ctx) {
                ctx.popItalic();
                ctx.popBold();
            }
        };
    }

    private static final class ColorTag implements RichTextTag.Handler {

        @Override
        public void open(RichTextContext ctx, List<String> args) {
            if (args.isEmpty()) return;
            try {
                @Nullable var color = resolveColor(args.getFirst());
                if (color != null) ctx.pushColor(color);
            } catch (Exception e) {
                Fragmentum.LOG.warn("[color] could not parse '{}': {}", args.get(0), e.getMessage());
            }
        }

        @Override
        public void close(RichTextContext ctx) {
            ctx.popColor();
        }

        private @Nullable Color resolveColor(String arg) {
            if (arg.startsWith("#")) return Color.parse(arg);
            @Nullable var named = ChatFormatting.getByName(arg);
            if (named != null && named.getColor() != null) return Color.packedRGB(named.getColor());
            return null;
        }
    }

    private static final class ChromaTag implements RichTextTag.Handler {

        @Override
        public void open(RichTextContext ctx, List<String> args) {
            try {
                Color first, second;
                float speed;

                if (args.size() == 1) {
                    @Nullable var preset = ChromaPresets.get(args.getFirst());
                    if (preset == null) {
                        Fragmentum.LOG.warn("[chroma] unknown preset '{}'", args.getFirst());
                        return;
                    }
                    first = preset.first();
                    second = preset.second();
                    speed = preset.speed();
                } else if (args.size() >= 2) {
                    first = Color.parse(args.get(0));
                    second = Color.parse(args.get(1));
                    speed = args.size() >= 3 ? Float.parseFloat(args.get(2)) : 1f;
                } else {
                    return;
                }

                float seconds = (System.currentTimeMillis() - START_TIME) / 1000f;
                float t = 0.5f + 0.5f * (float) Math.sin(seconds * speed);
                ctx.pushColor(first.lerp(second, t));
            } catch (Exception e) {
                Fragmentum.LOG.warn("[chroma] error: {}", e.getMessage());
            }
        }

        @Override
        public void close(RichTextContext ctx) {
            ctx.popColor();
        }
    }

    static {
        REGISTRY.put("i", new ItalicTag());
        REGISTRY.put("no-i", new ForceNonItalicTag());
        REGISTRY.put("b", new BoldTag());
        REGISTRY.put("no-b", new ForceNonBoldTag());
    }

    private record ItalicTag() implements RichTextTag.Handler {

        @Override
        public void open(RichTextContext ctx, List<String> args) {
            ctx.pushItalic(true);
        }

        @Override public void close(RichTextContext ctx) {
            ctx.popItalic();
        }
    }

    private record ForceNonItalicTag() implements RichTextTag.Handler {

        @Override
        public void open(RichTextContext ctx, List<String> args) {
            ctx.pushItalic(false);
        }

        @Override public void close(RichTextContext ctx) {
            ctx.popItalic();
        }
    }

    private record BoldTag() implements RichTextTag.Handler {

        @Override
        public void open(RichTextContext ctx, List<String> args) {
            ctx.pushBold(true);
        }

        @Override public void close(RichTextContext ctx) {
            ctx.popBold();
        }
    }

    private record ForceNonBoldTag() implements RichTextTag.Handler {

        @Override
        public void open(RichTextContext ctx, List<String> args) {
            ctx.pushBold(false);
        }

        @Override public void close(RichTextContext ctx) {
            ctx.popBold();
        }
    }

    private _RichTextTags() {}
}
