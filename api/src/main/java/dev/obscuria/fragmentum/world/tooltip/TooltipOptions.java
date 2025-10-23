package dev.obscuria.fragmentum.world.tooltip;

import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Function;

public record TooltipOptions(
        ImmutableMap<Integer, String> prefixByLine,
        ImmutableMap<Integer, Style> styleByLine,
        Function<String, MutableComponent> processor,
        String defaultPrefix,
        Style defaultStyle,
        int maxLineLength
) {

    public static final TooltipOptions DEFAULT = null;
    public static final TooltipOptions DESCRIPTION = null;
    public static final TooltipOptions LORE = null;
    public static final TooltipOptions ERROR = null;

    public static Builder builder() {
        throw new NotImplementedException();
    }

    public String getLinePrefix(int index) {
        throw new NotImplementedException();
    }

    public Style getLineStyle(int index) {
        throw new NotImplementedException();
    }

    public static class Builder {

        private Builder() {}

        public Builder withLineSpecificPrefix(int line, String prefix) {
            throw new NotImplementedException();
        }

        public Builder withLineSpecificStyle(int line, Style style) {
            throw new NotImplementedException();
        }

        public Builder withProcessor(Function<String, MutableComponent> processor) {
            throw new NotImplementedException();
        }

        public Builder withDefaultPrefix(String prefix) {
            throw new NotImplementedException();
        }

        public Builder withDefaultStyle(Style style) {
            throw new NotImplementedException();
        }

        public Builder withMaxLineLength(int maxLineLength) {
            throw new NotImplementedException();
        }

        public TooltipOptions build() {
            throw new NotImplementedException();
        }
    }
}