package dev.obscuria.fragmentum.world.tooltip;

import com.google.common.collect.ImmutableMap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

import java.util.Objects;
import java.util.function.Function;

public record TooltipOptions(
        ImmutableMap<Integer, String> prefixByLine,
        ImmutableMap<Integer, Style> styleByLine,
        Function<String, MutableComponent> processor,
        String defaultPrefix,
        Style defaultStyle,
        int maxLineLength
) {

    public static final TooltipOptions DEFAULT;
    public static final TooltipOptions DESCRIPTION;
    public static final TooltipOptions LORE;
    public static final TooltipOptions ERROR;

    public static Builder builder() {
        return new Builder();
    }

    public String getLinePrefix(int index) {
        return Objects.requireNonNull(prefixByLine.getOrDefault(index, defaultPrefix));
    }

    public Style getLineStyle(int index) {
        return Objects.requireNonNull(styleByLine.getOrDefault(index, defaultStyle));
    }

    static {
        DEFAULT = TooltipOptions.builder().build();
        DESCRIPTION = TooltipOptions.builder().withDefaultStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)).build();
        LORE = TooltipOptions.builder().withDefaultStyle(Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE)).build();
        ERROR = TooltipOptions.builder().withDefaultStyle(Style.EMPTY.withColor(ChatFormatting.RED)).build();
    }

    public static class Builder {

        private final ImmutableMap.Builder<Integer, String> prefixByLine = new ImmutableMap.Builder<>();
        private final ImmutableMap.Builder<Integer, Style> styleByLine = new ImmutableMap.Builder<>();
        private Function<String, MutableComponent> processor = Component::literal;
        private String defaultPrefix = "";
        private Style defaultStyle = Style.EMPTY;
        private int maxLineLength = 40;

        private Builder() {}

        public Builder withLineSpecificPrefix(int line, String prefix) {
            prefixByLine.put(line, prefix);
            return this;
        }

        public Builder withLineSpecificStyle(int line, Style style) {
            styleByLine.put(line, style);
            return this;
        }

        public Builder withProcessor(Function<String, MutableComponent> processor) {
            this.processor = processor;
            return this;
        }

        public Builder withDefaultPrefix(String prefix) {
            this.defaultPrefix = prefix;
            return this;
        }

        public Builder withDefaultStyle(Style style) {
            this.defaultStyle = style;
            return this;
        }

        public Builder withMaxLineLength(int maxLineLength) {
            this.maxLineLength = maxLineLength;
            return this;
        }

        public TooltipOptions build() {
            return new TooltipOptions(
                    prefixByLine.build(), styleByLine.build(), processor,
                    defaultPrefix, defaultStyle, maxLineLength);
        }
    }
}