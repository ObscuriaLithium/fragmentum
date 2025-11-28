package dev.obscuria.fragmentum.content.world.tooltip;

import dev.obscuria.fragmentum.content.util.color.RGB;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class TooltipBuilder {

    private final TooltipOptions options;

    private final List<Component> lines = new ArrayList<>();
    private final List<RGB> colors = new ArrayList<>();
    private final EnumSet<StyleFlag> flags = EnumSet.noneOf(StyleFlag.class);

    private MutableComponent line = Component.empty();
    private int lineLength = 0;
    private boolean isLineEmpty = true;

    public TooltipBuilder(TooltipOptions options) {
        this.options = options;
    }

    public void pushColor(RGB color) {
        colors.add(color);
    }

    public void popColor() {
        if (!colors.isEmpty()) {
            colors.remove(colors.size() - 1);
        }
    }

    public void pushFlag(StyleFlag flag) {
        flags.add(flag);
    }

    public void popFlag(StyleFlag flag) {
        flags.remove(flag);
    }

    public void breakLine() {
        flush();
    }

    public void prepareForAppend(int length) {
        if (lineLength + length + 1 > options.maxLineLength()) {
            flush();
        }
    }

    public void maybeAppendSpacing() {
        if (!isLineEmpty) {
            line.append(CommonComponents.SPACE);
            lineLength += 1;
        }
    }

    public void append(int length, MutableComponent component) {
        line.append(applyStyle(component));
        lineLength += length;
        isLineEmpty = false;
    }

    public List<Component> result() {
        if (!isLineEmpty) flush();
        return Collections.unmodifiableList(lines);
    }

    private MutableComponent applyStyle(MutableComponent component) {
        for (var color : colors) component.withStyle(component.getStyle().withColor(color.decimal()));
        if (flags.contains(StyleFlag.ITALIC)) component.withStyle(component.getStyle().withItalic(true));
        if (flags.contains(StyleFlag.NON_ITALIC)) component.withStyle(component.getStyle().withItalic(false));
        if (flags.contains(StyleFlag.BOLD)) component.withStyle(component.getStyle().withBold(true));
        if (flags.contains(StyleFlag.NON_BOLD)) component.withStyle(component.getStyle().withBold(false));
        return component;
    }

    private void flush() {
        lines.add(line.withStyle(options.getLineStyle(lines.size())));
        line = Component.empty();
        isLineEmpty = true;
        lineLength = 0;
    }
}