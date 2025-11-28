package dev.obscuria.fragmentum.config;

import dev.obscuria.fragmentum.Fragmentum;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class ConfigBuilder {

    public final ModConfigSpec.Builder specBuilder;
    public final String fileName;

    public ConfigBuilder(String fileName) {
        this.specBuilder = new ModConfigSpec.Builder();
        this.fileName = fileName;
    }

    public ConfigBuilder comment(String comment) {
        this.specBuilder.comment(comment);
        return this;
    }

    public ConfigBuilder comment(String... comment) {
        this.specBuilder.comment(comment);
        return this;
    }

    public void push(String name) {
        this.specBuilder.push(name);
    }

    public void pop() {
        this.specBuilder.pop();
    }

    public <T> ConfigValue<T> define(String path, T defaultValue) {
        return new ConfigValue<>(specBuilder.define(path, defaultValue));
    }

    public ConfigValue<Boolean> defineBoolean(String path, boolean defaultValue) {
        return new ConfigValue<>(specBuilder.define(path, defaultValue));
    }

    public ConfigValue<Integer> defineInt(String path, int defaultValue, int min, int max) {
        return new ConfigValue<>(specBuilder.defineInRange(path, defaultValue, min, max));
    }

    public ConfigValue<Double> defineDouble(String path, double defaultValue, double min, double max) {
        return new ConfigValue<>(specBuilder.defineInRange(path, defaultValue, min, max));
    }

    public ConfigValue<String> defineString(String path, String defaultValue) {
        return new ConfigValue<>(specBuilder.define(path, defaultValue));
    }

    public <T extends Enum<T>> ConfigValue<T> DefineEnum(String path, T defaultValue) {
        return new ConfigValue<>(specBuilder.defineEnum(path, defaultValue));
    }

    public <T> ConfigValue<List<? extends T>> defineList(String path, List<T> defaultValue, Supplier<T> newElementSupplier) {
        return defineList(path, defaultValue, newElementSupplier, it -> true);
    }

    public <T> ConfigValue<List<? extends T>> defineList(String path, List<T> defaultValue, Supplier<T> newElementSupplier, Predicate<Object> predicate) {
        return new ConfigValue<>(specBuilder.defineListAllowEmpty(path, defaultValue, newElementSupplier, predicate));
    }

    public void buildClient(String modId) {
        Fragmentum.SERVICES.config().registerClient(modId, this);
    }

    public void buildCommon(String modId) {
        Fragmentum.SERVICES.config().registerCommon(modId, this);
    }

    public void buildServer(String modId) {
        Fragmentum.SERVICES.config().registerServer(modId, this);
    }
}
