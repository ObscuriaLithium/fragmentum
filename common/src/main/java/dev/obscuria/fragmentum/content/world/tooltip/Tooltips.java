package dev.obscuria.fragmentum.content.world.tooltip;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tooltips {

    private static final char SPACING = ' ';
    private static final String DELIMITER = ", ";
    private static final String CLOSING_MARKER = "/";

    private static final Pattern TAG_PATTERN = Pattern.compile("\\[(/?)([\\w-]+)(?:=([^]\\[]+))?]");
    private static final Pattern TEMPLATE_PATTERN = Pattern.compile("<([\\w-]+)=([^>]*)>");

    public static List<Component> process(Component input) {
        return process(input.getString(), null, TooltipOptions.DEFAULT);
    }

    public static List<Component> process(Component input, Object source) {
        return process(input.getString(), source, TooltipOptions.DEFAULT);
    }

    public static List<Component> process(Component input, Object source, TooltipOptions options) {
        return process(input.getString(), source, options);
    }

    public static List<Component> process(String input, @Nullable Object source, TooltipOptions options) {
        final var builder = new TooltipBuilder(options);
        final var nodes = parseNodes(input, source);
        for (var node : nodes) node.process(builder, options);
        return builder.result();
    }

    private static List<Node> parseNodes(String input, @Nullable Object source) {

        final var group = new ArrayList<Node>();
        final var nodes = new ArrayList<Node>();
        final var matches = new ArrayList<MatchResult>();

        final var tagMatcher = TAG_PATTERN.matcher(input);
        while (tagMatcher.find()) {
            matches.add(new MatchResult(tagMatcher.start(), tagMatcher.end(), tagMatcher));
        }

        final var templateMatcher = TEMPLATE_PATTERN.matcher(input);
        while (templateMatcher.find()) {
            matches.add(new MatchResult(templateMatcher.start(), templateMatcher.end(), templateMatcher));
        }

        matches.sort(Comparator.comparingInt(a -> a.start));

        var lastIndex = 0;
        for (var match : matches) {
            final var before = input.substring(lastIndex, match.start);
            if (!before.isEmpty()) {
                final var shouldGroupFirst = !nodes.isEmpty() && !before.startsWith(String.valueOf(SPACING));
                final var shouldGroupLast = !before.endsWith(String.valueOf(SPACING));
                final var elements = before.split(String.valueOf(SPACING));
                for (var i = 0; i < elements.length; i++) {
                    final var element = elements[i];
                    if (element.isEmpty()) continue;
                    if (shouldGroupFirst && i == 0) {
                        group.add(new Node.Text(element));
                    } else if (shouldGroupLast && i == elements.length - 1) {
                        if (element.length() > 1) flushGroup(group, nodes);
                        group.add(new Node.Text(element));
                    } else {
                        flushGroup(group, nodes);
                        nodes.add(new Node.Text(element));
                    }
                }
            }

            if (match.matcher.group().startsWith("[")) {
                final var key = match.matcher.group(2);
                if (!CLOSING_MARKER.equals(match.matcher.group(1))) {
                    final var rawArgs = match.matcher.group(3) != null ? match.matcher.group(3).split(DELIMITER) : new String[0];
                    final var args = new ArrayList<String>();
                    for (var arg : rawArgs) if (!arg.isEmpty()) args.add(arg);
                    group.add(new Node.OpeningTag(key, args));
                } else {
                    group.add(new Node.ClosingTag(key));
                }
            } else {
                final var key = match.matcher.group(1);
                final var rawArgs = match.matcher.group(2).split(DELIMITER);
                final var args = new ArrayList<String>();
                for (var arg : rawArgs) if (!arg.isEmpty()) args.add(arg);
                group.add(new Node.Template(source, key, args));
            }

            lastIndex = match.end;
        }

        String remaining = input.substring(lastIndex);
        if (!remaining.isEmpty()) {
            boolean shouldGroupFirst = !remaining.startsWith(String.valueOf(SPACING));
            String[] elements = remaining.split(String.valueOf(SPACING));
            for (int i = 0; i < elements.length; i++) {
                String element = elements[i];
                if (element.isEmpty()) continue;
                if (shouldGroupFirst && i == 0) {
                    group.add(new Node.Text(element));
                } else {
                    flushGroup(group, nodes);
                    nodes.add(new Node.Text(element));
                }
            }
        }

        flushGroup(group, nodes);
        return nodes;
    }

    private static void flushGroup(List<Node> group, List<Node> nodes) {

        if (group.isEmpty()) return;
        if (group.size() == 1) {
            nodes.add(group.remove(0));
        } else {
            nodes.add(new Node.Group(new ArrayList<>(group)));
            group.clear();
        }
    }

    private static class MatchResult {

        int start;
        int end;
        Matcher matcher;

        MatchResult(int start, int end, Matcher matcher) {
            this.start = start;
            this.end = end;
            this.matcher = matcher;
        }
    }

    private abstract static class Node {

        abstract int length();
        abstract void process(TooltipBuilder builder, TooltipOptions options, boolean grouped);

        void process(TooltipBuilder builder, TooltipOptions options) {
            process(builder, options, false);
        }

        public static class Text extends Node {
            private final String content;

            public Text(String content) {
                this.content = content;
            }

            @Override
            int length() {
                return content.length();
            }

            @Override
            void process(TooltipBuilder builder, TooltipOptions options, boolean grouped) {
                if (!grouped) {
                    builder.prepareForAppend(length());
                    builder.maybeAppendSpacing();
                }
                builder.append(length(), options.processor().apply(content));
            }
        }

        public static class Template extends Node {

            private final @Nullable Object source;
            private final String key;
            private final List<String> args;
            private String text;

            public Template(@Nullable Object source, String key, List<String> args) {
                this.source = source;
                this.key = key;
                this.args = args;
            }

            @Override
            int length() {
                ensureText();
                return text.length();
            }

            private void ensureText() {
                if (text == null) {
                    text = TooltipTemplates.process(source, key, args);
                }
            }

            @Override
            void process(TooltipBuilder builder, TooltipOptions options, boolean grouped) {
                ensureText();
                if (!grouped) {
                    builder.prepareForAppend(length());
                    builder.maybeAppendSpacing();
                }
                builder.append(length(), Component.literal(text));
            }
        }

        public static class OpeningTag extends Node {

            private final String key;
            private final List<String> args;

            public OpeningTag(String key, List<String> args) {
                this.key = key;
                this.args = args;
            }

            @Override
            int length() { return 0; }

            @Override
            void process(TooltipBuilder builder, TooltipOptions options, boolean grouped) {
                TooltipTags.open(builder, key, args);
            }
        }

        public static class ClosingTag extends Node {

            private final String key;

            public ClosingTag(String key) {
                this.key = key;
            }

            @Override
            int length() { return 0; }

            @Override
            void process(TooltipBuilder builder, TooltipOptions options, boolean grouped) {
                TooltipTags.close(builder, key);
            }
        }

        public static class Group extends Node {

            private final List<Node> nodes;

            public Group(List<Node> nodes) {
                this.nodes = nodes;
            }

            @Override
            int length() {
                return nodes.stream().mapToInt(Node::length).sum();
            }

            @Override
            void process(TooltipBuilder builder, TooltipOptions options, boolean grouped) {
                if (length() > 0) {
                    builder.prepareForAppend(length());
                    builder.maybeAppendSpacing();
                }
                for (Node node : nodes) {
                    node.process(builder, options, true);
                }
            }
        }
    }
}