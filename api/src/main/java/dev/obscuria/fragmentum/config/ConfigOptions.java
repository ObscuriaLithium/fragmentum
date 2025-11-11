package dev.obscuria.fragmentum.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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
}
