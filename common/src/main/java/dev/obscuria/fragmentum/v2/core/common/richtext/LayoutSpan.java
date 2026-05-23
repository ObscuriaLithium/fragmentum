package dev.obscuria.fragmentum.v2.core.common.richtext;

import dev.obscuria.fragmentum.v2.api.common.Color;
import net.minecraft.network.chat.Style;
import org.jetbrains.annotations.Nullable;

record LayoutSpan(
        String text,
        @Nullable Color color,
        @Nullable Boolean italic,
        @Nullable Boolean bold
) {

    Style applyTo(Style base) {
        var s = base;
        if (color != null) s = s.withColor(color.rgb());
        if (italic != null) s = s.withItalic(italic);
        if (bold != null) s = s.withBold(bold);
        return s;
    }

    boolean isEmpty() {
        return text.isEmpty();
    }
}
