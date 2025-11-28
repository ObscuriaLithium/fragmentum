package dev.obscuria.fragmentum.config;

import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public final class ConfigBuilder {

    public ConfigBuilder() {}

    public ConfigBuilder(String fileName) {}

    public ConfigBuilder comment(String comment) {
        throw new NotImplementedException();
    }

    public ConfigBuilder comment(String... comment) {
        throw new NotImplementedException();
    }

    public void push(String name) {
        throw new NotImplementedException();
    }

    public void pop() {
        throw new NotImplementedException();
    }

    public <T> ConfigValue<T> define(String path, T defaultValue) {
        throw new NotImplementedException();
    }

    public ConfigValue<Boolean> defineBoolean(String path, boolean defaultValue) {
        throw new NotImplementedException();
    }

    public ConfigValue<Integer> defineInt(String path, int defaultValue, int min, int max) {
        throw new NotImplementedException();
    }

    public ConfigValue<Double> defineDouble(String path, double defaultValue, double min, double max) {
        throw new NotImplementedException();
    }

    public ConfigValue<String> defineString(String path, String defaultValue) {
        throw new NotImplementedException();
    }

    public <T extends Enum<T>> ConfigValue<T> DefineEnum(String path, T defaultValue) {
        throw new NotImplementedException();
    }

    public <T> ConfigValue<List<? extends T>> defineList(String path, List<? extends T> defaultValue) {
        throw new NotImplementedException();
    }

    public <T> ConfigValue<List<? extends T>> defineList(String path, List<? extends T> defaultValue, Predicate<Object> predicate) {
        throw new NotImplementedException();
    }

    public void buildClient(String modId) {
        throw new NotImplementedException();
    }

    public void buildCommon(String modId) {
        throw new NotImplementedException();
    }

    public void buildServer(String modId) {
        throw new NotImplementedException();
    }
}
