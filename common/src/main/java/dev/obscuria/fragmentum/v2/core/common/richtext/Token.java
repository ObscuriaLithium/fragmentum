package dev.obscuria.fragmentum.v2.core.common.richtext;

import org.jetbrains.annotations.Nullable;

import java.util.List;

sealed interface Token permits
        Token.Word,
        Token.Whitespace,
        Token.OpenTag,
        Token.CloseTag,
        Token.TemplateExpansion,
        Token.LineBreak {

    record Word(String text) implements Token {}

    record Whitespace() implements Token {}

    record OpenTag(String key, List<String> args) implements Token {}

    record CloseTag(String key) implements Token {}

    record TemplateExpansion(@Nullable Object source, String key, List<String> args) implements Token {}

    record LineBreak() implements Token {}
}
