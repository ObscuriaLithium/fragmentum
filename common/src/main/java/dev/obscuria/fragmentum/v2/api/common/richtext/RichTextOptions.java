package dev.obscuria.fragmentum.v2.api.common.richtext;

import com.google.common.collect.ImmutableMap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class RichTextOptions {

    public static final RichTextOptions DEFAULT = builder().build();
    public static final RichTextOptions DESCRIPTION = builder()
            .defaultStyle(Style.EMPTY.withColor(ChatFormatting.GRAY))
            .build();
    public static final RichTextOptions LORE = builder()
            .defaultStyle(Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE))
            .build();
    public static final RichTextOptions ERROR = builder()
            .defaultStyle(Style.EMPTY.withColor(ChatFormatting.RED))
            .build();

    private final ImmutableMap<Integer, String> prefixByLine;
    private final ImmutableMap<Integer, Style> styleByLine;
    private final Function<String, MutableComponent> textProcessor;
    private final String defaultPrefix;
    private final Style defaultStyle;
    private final int maxLineLength;

    public static Builder builder() {
        return new Builder();
    }

    RichTextOptions(
            ImmutableMap<Integer, String> prefixByLine,
            ImmutableMap<Integer, Style> styleByLine,
            Function<String, MutableComponent> textProcessor,
            String defaultPrefix,
            Style defaultStyle,
            int maxLineLength
    ) {
        this.prefixByLine = prefixByLine;
        this.styleByLine = styleByLine;
        this.textProcessor = textProcessor;
        this.defaultPrefix = defaultPrefix;
        this.defaultStyle = defaultStyle;
        this.maxLineLength = maxLineLength;
    }

    public String prefixForLine(int index) {
        return Objects.requireNonNull(prefixByLine.getOrDefault(index, defaultPrefix));
    }

    public Style styleForLine(int index) {
        return Objects.requireNonNull(styleByLine.getOrDefault(index, defaultStyle));
    }

    public Function<String, MutableComponent> textProcessor() {
        return textProcessor;
    }

    public String defaultPrefix() {
        return defaultPrefix;
    }

    public Style defaultStyle() {
        return defaultStyle;
    }

    public int maxLineLength() {
        return maxLineLength;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static final class Builder {

        private final Map<Integer, String> prefixByLine = new HashMap<>();
        private final Map<Integer, Style> styleByLine = new HashMap<>();
        private Function<String, MutableComponent> textProcessor = Component::literal;
        private String defaultPrefix = "";
        private Style defaultStyle = Style.EMPTY;
        private int maxLineLength = 40;

        private Builder() {}

        private Builder(RichTextOptions src) {
            this.prefixByLine.putAll(src.prefixByLine);
            this.styleByLine.putAll(src.styleByLine);
            this.textProcessor = src.textProcessor;
            this.defaultPrefix = src.defaultPrefix;
            this.defaultStyle = src.defaultStyle;
            this.maxLineLength = src.maxLineLength;
        }

        public Builder linePrefix(int line, String prefix) {
            prefixByLine.put(line, prefix);
            return this;
        }

        public Builder lineStyle(int line, Style style) {
            styleByLine.put(line, style);
            return this;
        }

        public Builder textProcessor(Function<String, MutableComponent> processor) {
            this.textProcessor = processor;
            return this;
        }

        public Builder defaultPrefix(String prefix) {
            this.defaultPrefix = prefix;
            return this;
        }

        public Builder defaultStyle(Style style) {
            this.defaultStyle = style;
            return this;
        }

        public Builder maxLineLength(int length) {
            this.maxLineLength = length;
            return this;
        }

        public RichTextOptions build() {
            return new RichTextOptions(
                    ImmutableMap.copyOf(prefixByLine),
                    ImmutableMap.copyOf(styleByLine),
                    textProcessor,
                    defaultPrefix,
                    defaultStyle,
                    maxLineLength);
        }
    }
}
