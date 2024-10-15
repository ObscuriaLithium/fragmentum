package dev.obscuria.fragmentum.neoforge.config;

import dev.obscuria.fragmentum.api.v1.common.config.IConfigSpec;
import dev.obscuria.fragmentum.api.v1.common.config.IConfigBuilder;
import dev.obscuria.fragmentum.api.v1.common.config.IConfigValue;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class NeoConfigBuilder implements IConfigBuilder {
    private final ModConfigSpec.Builder builder;

    public NeoConfigBuilder(ModConfigSpec.Builder builder) {
        this.builder = builder;
    }

    @Override
    public <T> IConfigValue<T> define(String path,
                                      T defaultValue) {

        return new NeoConfigValue<>(builder.define(path, defaultValue));
    }

    @Override
    public <T> IConfigValue<T> define(String path,
                                      Supplier<T> defaultSupplier,
                                      Predicate<Object> validator) {

        return new NeoConfigValue<>(builder.define(path, defaultSupplier, validator));
    }

    @Override
    public <T> IConfigValue<List<? extends T>> defineList(String path,
                                                          List<? extends T> defaultValue,
                                                          Supplier<T> newElementSupplier,
                                                          Predicate<Object> elementValidator) {

        return new NeoConfigValue<>(builder.defineList(path, defaultValue, newElementSupplier, elementValidator));
    }

    @Override
    public <T> IConfigValue<List<? extends T>> defineList(String path,
                                                          Supplier<List<? extends T>> defaultSupplier,
                                                          Supplier<T> newElementSupplier,
                                                          Predicate<Object> elementValidator) {

        return new NeoConfigValue<>(builder.defineList(path, defaultSupplier, newElementSupplier, elementValidator));
    }

    @Override
    public <T> IConfigValue<List<? extends T>> defineListAllowEmpty(String path,
                                                                    List<? extends T> defaultValue,
                                                                    Supplier<T> newElementSupplier,
                                                                    Predicate<Object> elementValidator) {

        return new NeoConfigValue<>(builder.defineListAllowEmpty(path, defaultValue, newElementSupplier, elementValidator));
    }

    @Override
    public <T> IConfigValue<List<? extends T>> defineListAllowEmpty(String path,
                                                                    Supplier<List<? extends T>> defaultSupplier,
                                                                    Supplier<T> newElementSupplier,
                                                                    Predicate<Object> elementValidator) {
        return new NeoConfigValue<>(builder.defineListAllowEmpty(path, defaultSupplier, newElementSupplier, elementValidator));
    }

    @Override
    public <V extends Enum<V>> IConfigValue<V> defineEnum(String path,
                                                          V defaultValue) {

        return new NeoConfigValue<>(builder.defineEnum(path, defaultValue));
    }

    @Override
    public <V extends Enum<V>> IConfigValue<V> defineEnum(String path,
                                                          V defaultValue,
                                                          Collection<V> acceptableValues) {

        return new NeoConfigValue<>(builder.defineEnum(path, defaultValue, acceptableValues));
    }

    @Override
    public IConfigValue<Boolean> define(String path,
                                        boolean defaultValue) {

        return new NeoConfigValue<>(builder.define(path, defaultValue));
    }

    @Override
    public IConfigValue<Boolean> define(String path,
                                        Supplier<Boolean> defaultSupplier) {

        return new NeoConfigValue<>(builder.define(path, defaultSupplier));
    }

    @Override
    public IConfigValue<Double> defineInRange(String path,
                                              double defaultValue,
                                              double min,
                                              double max) {

        return new NeoConfigValue<>(builder.defineInRange(path, defaultValue, min, max));
    }

    @Override
    public IConfigValue<Double> defineInRange(String path,
                                              Supplier<Double> defaultSupplier,
                                              double min,
                                              double max) {

        return new NeoConfigValue<>(builder.defineInRange(path, defaultSupplier, min, max));
    }

    @Override
    public IConfigValue<Integer> defineInRange(String path,
                                               int defaultValue,
                                               int min,
                                               int max) {

        return new NeoConfigValue<>(builder.defineInRange(path, defaultValue, min, max));
    }

    @Override
    public IConfigValue<Integer> defineInRange(String path,
                                               Supplier<Integer> defaultSupplier,
                                               int min,
                                               int max) {

        return new NeoConfigValue<>(builder.defineInRange(path, defaultSupplier, min, max));
    }

    @Override
    public IConfigValue<Long> defineInRange(String path,
                                            long defaultValue,
                                            long min,
                                            long max) {

        return new NeoConfigValue<>(builder.defineInRange(path, defaultValue, min, max));
    }

    @Override
    public IConfigValue<Long> defineInRange(String path,
                                            Supplier<Long> defaultSupplier,
                                            long min,
                                            long max) {

        return new NeoConfigValue<>(builder.defineInRange(path, defaultSupplier, min, max));
    }

    @Override
    public IConfigBuilder comment(String comment) {
        this.builder.comment(comment);
        return this;
    }

    @Override
    public IConfigBuilder comment(String... comment) {
        this.builder.comment(comment);
        return this;
    }

    @Override
    public IConfigBuilder worldRestart() {
        this.builder.worldRestart();
        return this;
    }

    @Override
    public IConfigBuilder gameRestart() {
        this.builder.gameRestart();
        return this;
    }

    @Override
    public IConfigBuilder push(String path) {
        this.builder.push(path);
        return this;
    }

    @Override
    public IConfigBuilder pop() {
        this.builder.pop();
        return this;
    }

    @Override
    public IConfigSpec build() {
        return new NeoConfigSpec(builder.build());
    }
}
