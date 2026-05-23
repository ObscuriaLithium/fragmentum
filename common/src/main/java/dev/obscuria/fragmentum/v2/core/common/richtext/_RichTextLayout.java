package dev.obscuria.fragmentum.v2.core.common.richtext;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
final class _RichTextLayout {

    static void layout(List<Token> tokens, _RichTextBuilder builder) {
        var groups = buildGroups(tokens);

        boolean needSpace = false;

        for (var group : groups) {
            switch (group) {
                case Group.Break b -> {
                    dispatchTokens(b.tokens(), builder);
                    builder.breakLine();
                    needSpace = false;
                }
                case Group.Space s -> {
                    dispatchZeroWidthOnly(s.tokens(), builder);
                    needSpace = true;
                }
                case Group.Word w -> {
                    int wordLen = wordLength(w.tokens());

                    if (needSpace) {
                        int needed = (builder.currentLineLength() > 0 ? 1 : 0) + wordLen;
                        if (builder.currentLineLength() + needed > builder.maxLineLength()
                                && builder.currentLineLength() > 0) {
                            builder.breakLine();
                        } else if (builder.currentLineLength() > 0) {
                            builder.appendSpace();
                        }
                        needSpace = false;
                    }

                    dispatchTokens(w.tokens(), builder);
                }
            }
        }
    }

    private sealed interface Group permits Group.Word, Group.Space, Group.Break {

        List<Token> tokens();

        record Word(List<Token> tokens) implements Group {}

        record Space(List<Token> tokens) implements Group {}

        record Break(List<Token> tokens) implements Group {}
    }

    private static List<Group> buildGroups(List<Token> tokens) {
        var groups = new ArrayList<Group>();
        var current = new ArrayList<Token>();
        @Nullable Boolean inSpace = null;

        for (var token : tokens) {
            switch (token) {
                case Token.LineBreak lb -> {
                    if (!current.isEmpty()) {
                        groups.add(inSpace == Boolean.TRUE
                                ? new Group.Space(List.copyOf(current))
                                : new Group.Word(List.copyOf(current)));
                        current.clear();
                    }
                    groups.add(new Group.Break(List.of(lb)));
                    inSpace = null;
                }
                case Token.Whitespace ws -> {
                    if (inSpace == Boolean.FALSE) {
                        groups.add(new Group.Word(List.copyOf(current)));
                        current.clear();
                    }
                    current.add(token);
                    inSpace = true;
                }
                case Token.Word w -> {
                    if (inSpace == Boolean.TRUE) {
                        groups.add(new Group.Space(List.copyOf(current)));
                        current.clear();
                    }
                    current.add(token);
                    inSpace = false;
                }
                default -> {
                    if (inSpace == null) inSpace = false;
                    current.add(token);
                }
            }
        }

        if (!current.isEmpty()) {
            groups.add(inSpace == Boolean.TRUE
                    ? new Group.Space(List.copyOf(current))
                    : new Group.Word(List.copyOf(current)));
        }

        return groups;
    }

    private static void dispatchTokens(List<Token> tokens, _RichTextBuilder builder) {
        for (var token : tokens) {
            switch (token) {
                case Token.Word(var text) -> builder.appendWord(text);
                case Token.OpenTag(var key, var args) -> _RichTextTags.open(builder, key, args);
                case Token.CloseTag(var key) -> _RichTextTags.close(builder, key);
                case Token.TemplateExpansion(var src, var k, var a) -> {
                    var text = _RichTextTemplates.evaluate(src, k, a);
                    builder.appendWord(text);
                }
                case Token.Whitespace ws -> { /* consumed at group level */ }
                case Token.LineBreak lb -> builder.breakLine();
            }
        }
    }

    private static void dispatchZeroWidthOnly(List<Token> tokens, _RichTextBuilder builder) {
        for (var token : tokens) {
            switch (token) {
                case Token.OpenTag(var key, var args) -> _RichTextTags.open(builder, key, args);
                case Token.CloseTag(var key) -> _RichTextTags.close(builder, key);
                default -> { /* whitespace — skip */ }
            }
        }
    }

    private static int wordLength(List<Token> tokens) {
        int len = 0;
        for (var token : tokens) {
            if (token instanceof Token.Word(String text)) len += text.length();
            if (token instanceof Token.TemplateExpansion(Object source, String key, List<String> args)) {
                len += _RichTextTemplates.evaluate(source, key, args).length();
            }
        }
        return len;
    }
}
