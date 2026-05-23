package dev.obscuria.fragmentum.v2.api.common.richtext;

import dev.obscuria.fragmentum.v2.api.common.Color;

@SuppressWarnings("unused")
public interface RichTextContext {

    void pushColor(Color color);

    void popColor();

    void pushItalic(boolean italic);

    void popItalic();

    void pushBold(boolean bold);

    void popBold();

    void breakLine();

    Color currentColor();

    Boolean currentItalic();

    Boolean currentBold();

    int completedLineCount();
}
