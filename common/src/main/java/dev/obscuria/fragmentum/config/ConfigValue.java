package dev.obscuria.fragmentum.config;

import net.neoforged.neoforge.common.ModConfigSpec;

@SuppressWarnings("all")
public record ConfigValue<T>(ModConfigSpec.ConfigValue<T> value) {

    public T get() {
        return value.get();
    }

    public T getDefault() {
        return value.getDefault();
    }

    public void set(T value) {
        this.value.set(value);
    }

    public void save() {
        this.value.save();
    }
}
