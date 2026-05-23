package dev.obscuria.fragmentum.v2.core.config;

import com.google.common.base.Predicates;
import dev.obscuria.fragmentum.v2.api.config.ConfigBuilder;
import dev.obscuria.fragmentum.v2.api.config.ConfigValue;
import dev.obscuria.fragmentum.Fragmentum;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class _ConfigBuilder implements ConfigBuilder {

    private final HashSet<ConfigValue<?>> values = new HashSet<>();
    private final ModConfigSpec.Builder specBuilder;
    private final String fileName;

    public _ConfigBuilder(String fileName) {
        this.specBuilder = new ModConfigSpec.Builder();
        this.fileName = fileName;
    }

    @Override
    public ConfigBuilder comment(String comment) {
        this.specBuilder.comment(comment);
        return this;
    }

    @Override
    public ConfigBuilder comment(String... comment) {
        this.specBuilder.comment(comment);
        return this;
    }

    @Override
    public void pushCategory(String name) {
        this.specBuilder.push(name);
    }

    @Override
    public void popCategory() {
        this.specBuilder.pop();
    }

    @Override
    public <T> ConfigValue<T> define(String path, T defaultValue) {
        return register(new _ConfigValue<>(specBuilder.define(path, defaultValue)));
    }

    @Override
    public ConfigValue<Boolean> defineBoolean(String path, boolean defaultValue) {
        return register(new _ConfigValue<>(specBuilder.define(path, defaultValue)));
    }

    @Override
    public ConfigValue<Integer> defineInt(String path, int defaultValue, int min, int max) {
        return register(new _ConfigValue<>(specBuilder.defineInRange(path, defaultValue, min, max)));
    }

    @Override
    public ConfigValue<Double> defineDouble(String path, double defaultValue, double min, double max) {
        return register(new _ConfigValue<>(specBuilder.defineInRange(path, defaultValue, min, max)));
    }

    @Override
    public ConfigValue<String> defineString(String path, String defaultValue) {
        return register(new _ConfigValue<>(specBuilder.define(path, defaultValue)));
    }

    @Override
    public <T extends Enum<T>> ConfigValue<T> DefineEnum(String path, T defaultValue) {
        return register(new _ConfigValue<>(specBuilder.defineEnum(path, defaultValue)));
    }

    @Override
    public <T> ConfigValue<List<? extends T>> defineList(String path, List<T> defaultValue, Supplier<T> newElementSupplier) {
        return defineList(path, defaultValue, newElementSupplier, Predicates.alwaysTrue());
    }

    @Override
    public <T> ConfigValue<List<? extends T>> defineList(String path, List<T> defaultValue, Supplier<T> newElementSupplier, Predicate<Object> predicate) {
        return register(new _ConfigValue<>(specBuilder.defineListAllowEmpty(path, defaultValue, newElementSupplier, predicate)));
    }

    @Override
    public HashSet<ConfigValue<?>> buildClient(String modId) {
        Fragmentum.SERVICES.config().registerClient(modId, fileName, specBuilder);
        return values;
    }

    @Override
    public HashSet<ConfigValue<?>> buildCommon(String modId) {
        Fragmentum.SERVICES.config().registerCommon(modId, fileName, specBuilder);
        return values;
    }

    @Override
    public HashSet<ConfigValue<?>> buildServer(String modId) {
        Fragmentum.SERVICES.config().registerServer(modId, fileName, specBuilder);
        return values;
    }

    private <T> ConfigValue<T> register(ConfigValue<T> value) {
        if (values.contains(value)) throw new IllegalStateException("Duplicate value: " + value);
        this.values.add(value);
        return value;
    }
}
