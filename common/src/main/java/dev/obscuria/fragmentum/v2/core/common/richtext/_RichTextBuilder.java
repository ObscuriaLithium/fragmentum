package dev.obscuria.fragmentum.v2.core.common.richtext;

import dev.obscuria.fragmentum.v2.api.common.Color;
import dev.obscuria.fragmentum.v2.api.common.richtext.RichTextContext;
import dev.obscuria.fragmentum.v2.api.common.richtext.RichTextOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

final class _RichTextBuilder implements RichTextContext {

    private final RichTextOptions options;

    private final Deque<Color> colorStack = new ArrayDeque<>();
    private final Deque<Boolean> italicStack = new ArrayDeque<>();
    private final Deque<Boolean> boldStack = new ArrayDeque<>();

    private final List<LayoutSpan> currentSpans = new ArrayList<>();
    private int currentLength = 0;
    private boolean lineHasContent = false;

    private final List<List<LayoutSpan>> completedLines = new ArrayList<>();

    _RichTextBuilder(RichTextOptions options) {
        this.options = options;
    }

    @Override public void pushColor(Color color) {colorStack.push(color);}

    @Override public void popColor() {if (!colorStack.isEmpty()) colorStack.pop();}

    @Override public void pushItalic(boolean italic) {italicStack.push(italic);}

    @Override public void popItalic() {if (!italicStack.isEmpty()) italicStack.pop();}

    @Override public void pushBold(boolean bold) {boldStack.push(bold);}

    @Override public void popBold() {if (!boldStack.isEmpty()) boldStack.pop();}

    @Override public void breakLine() {flushLine();}

    @Override public Color currentColor() {return colorStack.isEmpty() ? null : colorStack.peek();}

    @Override public Boolean currentItalic() {return italicStack.isEmpty() ? null : italicStack.peek();}

    @Override public Boolean currentBold() {return boldStack.isEmpty() ? null : boldStack.peek();}

    @Override public int completedLineCount() {return completedLines.size();}

    int currentLineLength() {return currentLength;}

    int maxLineLength() {return options.maxLineLength();}

    void appendWord(String text) {
        if (text.isEmpty()) return;
        currentSpans.add(snapshot(text));
        currentLength += text.length();
        lineHasContent = true;
    }

    void appendSpace() {
        if (!lineHasContent) return;
        currentSpans.add(snapshot(" "));
        currentLength += 1;
    }

    List<Component> build() {
        if (lineHasContent) flushLine();
        var result = new ArrayList<Component>(completedLines.size());
        for (int i = 0; i < completedLines.size(); i++) {
            result.add(assembleLine(i, completedLines.get(i)));
        }
        return Collections.unmodifiableList(result);
    }

    private LayoutSpan snapshot(String text) {
        return new LayoutSpan(
                text,
                colorStack.isEmpty() ? null : colorStack.peek(),
                italicStack.isEmpty() ? null : italicStack.peek(),
                boldStack.isEmpty() ? null : boldStack.peek()
        );
    }

    private void flushLine() {
        completedLines.add(List.copyOf(currentSpans));
        currentSpans.clear();
        currentLength = 0;
        lineHasContent = false;
    }

    private Component assembleLine(int lineIndex, List<LayoutSpan> spans) {
        var baseStyle = options.styleForLine(lineIndex);
        var prefix = options.prefixForLine(lineIndex);

        MutableComponent line = Component.empty().withStyle(baseStyle);
        if (!prefix.isEmpty()) {
            line.append(Component.literal(prefix).withStyle(baseStyle));
        }

        for (var span : spans) {
            if (span.isEmpty()) continue;
            var style = span.applyTo(baseStyle);
            var part = options.textProcessor().apply(span.text()).withStyle(style);
            line.append(part);
        }

        return line;
    }
}
