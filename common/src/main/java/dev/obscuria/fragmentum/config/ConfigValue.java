package dev.obscuria.fragmentum.config;

import net.minecraftforge.common.ForgeConfigSpec;

@SuppressWarnings("all")
public record ConfigValue<T>(String name, ForgeConfigSpec.ConfigValue<T> value) {

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
