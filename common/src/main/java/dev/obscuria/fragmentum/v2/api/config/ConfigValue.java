package dev.obscuria.fragmentum.v2.api.config;

@SuppressWarnings("unused")
public interface ConfigValue<T> {

    String name();

    T get();

    T getDefault();

    void set(T value);

    void save();
}
