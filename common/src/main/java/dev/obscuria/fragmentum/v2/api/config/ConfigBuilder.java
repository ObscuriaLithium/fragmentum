package dev.obscuria.fragmentum.v2.api.config;

import dev.obscuria.fragmentum.v2.core.config._ConfigBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface ConfigBuilder {

    static ConfigBuilder create(String fileName) {
        return new _ConfigBuilder(fileName);
    }

    ConfigBuilder comment(String comment);

    ConfigBuilder comment(String... comment);

    void pushCategory(String name);

    void popCategory();

    <T> ConfigValue<T> define(String path, T defaultValue);

    ConfigValue<Boolean> defineBoolean(String path, boolean defaultValue);

    ConfigValue<Integer> defineInt(String path, int defaultValue, int min, int max);

    ConfigValue<Double> defineDouble(String path, double defaultValue, double min, double max);

    ConfigValue<String> defineString(String path, String defaultValue);

    <T extends Enum<T>> ConfigValue<T> DefineEnum(String path, T defaultValue);

    <T> ConfigValue<List<? extends T>> defineList(String path, List<T> defaultValue, Supplier<T> newElementSupplier);

    <T> ConfigValue<List<? extends T>> defineList(String path, List<T> defaultValue, Supplier<T> newElementSupplier, Predicate<Object> predicate);

    HashSet<ConfigValue<?>> buildClient(String modId);

    HashSet<ConfigValue<?>> buildCommon(String modId);

    HashSet<ConfigValue<?>> buildServer(String modId);
}
