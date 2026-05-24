package dev.obscuria.fragmentum.v2.core.config;

import dev.obscuria.fragmentum.v2.api.config.ConfigValue;
import net.neoforged.neoforge.common.ModConfigSpec;

public record _ConfigValue<T>(String name, ModConfigSpec.ConfigValue<T> value) implements ConfigValue<T> {

    @Override
    public T get() {
        return value.get();
    }

    @Override
    public T getDefault() {
        return value.getDefault();
    }

    @Override
    public void set(T value) {
        this.value.set(value);
    }

    @Override
    public void save() {
        this.value.save();
    }
}
