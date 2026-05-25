package dev.obscuria.fragmentum.config;

import dev.obscuria.fragmentum.Fragmentum;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class ConfigBuilder {

    public final ModConfigSpec.Builder specBuilder;
    public final @Nullable String fileName;
    private final HashSet<ConfigValue<?>> values = new HashSet<>();

    public ConfigBuilder() {
        this.specBuilder = new ModConfigSpec.Builder();
        this.fileName = null;
    }

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
        return register(new ConfigValue<>(path, specBuilder.define(path, defaultValue)));
    }

    public ConfigValue<Boolean> defineBoolean(String path, boolean defaultValue) {
        return register(new ConfigValue<>(path, specBuilder.define(path, defaultValue)));
    }

    public ConfigValue<Integer> defineInt(String path, int defaultValue, int min, int max) {
        return register(new ConfigValue<>(path, specBuilder.defineInRange(path, defaultValue, min, max)));
    }

    public ConfigValue<Double> defineDouble(String path, double defaultValue, double min, double max) {
        return register(new ConfigValue<>(path, specBuilder.defineInRange(path, defaultValue, min, max)));
    }

    public ConfigValue<String> defineString(String path, String defaultValue) {
        return register(new ConfigValue<>(path, specBuilder.define(path, defaultValue)));
    }

    public <T extends Enum<T>> ConfigValue<T> DefineEnum(String path, T defaultValue) {
        return register(new ConfigValue<>(path, specBuilder.defineEnum(path, defaultValue)));
    }

    public <T> ConfigValue<List<? extends T>> defineList(String path, List<T> defaultValue, Supplier<T> newElementSupplier) {
        return defineList(path, defaultValue, newElementSupplier, it -> true);
    }

    public <T> ConfigValue<List<? extends T>> defineList(String path, List<T> defaultValue, Supplier<T> newElementSupplier, Predicate<Object> predicate) {
        return register(new ConfigValue<>(path, specBuilder.defineListAllowEmpty(path, defaultValue, newElementSupplier, predicate)));
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

    public HashSet<ConfigValue<?>> collectValues() {
        return values;
    }

    private <T> ConfigValue<T> register(ConfigValue<T> value) {
        values.add(value);
        return value;
    }
}
