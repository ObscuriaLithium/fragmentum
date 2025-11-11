package dev.obscuria.fragmentum.config;

import net.minecraft.client.resources.language.I18n;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public interface ConfigOptions {

    @Retention(RetentionPolicy.RUNTIME)
    @interface Section {

        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Value {

        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Comment {

        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Range {

        double min() default Double.MIN_VALUE;

        double max() default Double.MAX_VALUE;
    }

    static String getValueName(Field field) {
        final var value = field.getAnnotation(Value.class);
        return value == null ? field.getName() : value.value();
    }

    static void applyComment(Field field, Consumer<String> consumer) {
        final var comment = field.getAnnotation(Comment.class);
        if (comment != null) consumer.accept(I18n.get(comment.value()));
    }

    static double getRangeMin(Field field) {
        final var annotation = field.getAnnotation(Range.class);
        return annotation == null ? Double.MIN_VALUE : annotation.min();
    }

    static double getRangeMax(Field field) {
        final var annotation = field.getAnnotation(Range.class);
        return annotation == null ? Double.MAX_VALUE : annotation.max();
    }
}
