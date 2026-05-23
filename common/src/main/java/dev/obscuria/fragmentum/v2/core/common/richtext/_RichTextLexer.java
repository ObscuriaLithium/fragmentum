package dev.obscuria.fragmentum.v2.core.common.richtext;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@UtilityClass
final class _RichTextLexer {

    private static final Pattern TAG_PATTERN = Pattern.compile("\\[(/?)([\\w:-]+)(?:=([^]\\[]+))?]");
    private static final Pattern TEMPLATE_PATTERN = Pattern.compile("<([\\w:-]+)=([^>]*)>");
    private static final String ARG_DELIMITER = ",";

    static List<Token> tokenize(String input, @Nullable Object source) {

        record Span(int start, int end, boolean isTag, java.util.regex.Matcher m) {}
        var spans = new ArrayList<Span>();

        var tagMatcher = TAG_PATTERN.matcher(input);
        while (tagMatcher.find()) {
            spans.add(new Span(tagMatcher.start(), tagMatcher.end(), true,
                    tagMatcher.toMatchResult() instanceof java.util.regex.Matcher mm ? mm : tagMatcher));
        }
        var tmplMatcher = TEMPLATE_PATTERN.matcher(input);
        while (tmplMatcher.find()) {
            spans.add(new Span(tmplMatcher.start(), tmplMatcher.end(), false,
                    tmplMatcher.toMatchResult() instanceof java.util.regex.Matcher mm ? mm : tmplMatcher));
        }
        spans.sort(java.util.Comparator.comparingInt(s -> s.start));

        var tokens = new ArrayList<Token>();
        int cursor = 0;

        for (var span : spans) {
            if (span.start < cursor) continue;
            if (span.start > cursor) {
                tokenizeText(input.substring(cursor, span.start), tokens);
            }
            if (span.isTag) {
                emitTag(span.m, tokens);
            } else {
                emitTemplate(span.m, source, tokens);
            }
            cursor = span.end;
        }

        if (cursor < input.length()) {
            tokenizeText(input.substring(cursor), tokens);
        }

        return List.copyOf(tokens);
    }

    private static void tokenizeText(String text, List<Token> out) {
        if (text.isEmpty()) return;

        int i = 0;
        while (i < text.length()) {
            if (Character.isWhitespace(text.charAt(i))) {

                int start = i;
                while (i < text.length() && Character.isWhitespace(text.charAt(i))) i++;

                if (text.substring(start, i).contains("\n")) {
                    out.add(new Token.LineBreak());
                } else {
                    out.add(new Token.Whitespace());
                }
            } else {
                int start = i;
                while (i < text.length() && !Character.isWhitespace(text.charAt(i))) i++;
                out.add(new Token.Word(text.substring(start, i)));
            }
        }
    }

    private static void emitTag(java.util.regex.Matcher m, List<Token> out) {
        String full = m.group();
        boolean closing = "/".equals(m.group(1));
        String key = m.group(2);

        if (closing) {
            out.add(new Token.CloseTag(key));
        } else {
            out.add(new Token.OpenTag(key, parseArgs(m.group(3))));
        }
    }

    private static void emitTemplate(java.util.regex.Matcher m, @Nullable Object source, List<Token> out) {
        String key = m.group(1);
        String args = m.group(2);
        out.add(new Token.TemplateExpansion(source, key, parseArgs(args)));
    }

    private static List<String> parseArgs(@Nullable String raw) {
        if (raw == null || raw.isBlank()) return List.of();
        return Arrays.stream(raw.split(ARG_DELIMITER))
                .map(String::strip)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}
