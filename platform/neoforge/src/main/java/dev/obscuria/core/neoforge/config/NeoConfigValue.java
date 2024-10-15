package dev.obscuria.fragmentum.neoforge.config;

import dev.obscuria.fragmentum.api.v1.common.config.IConfigValue;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class NeoConfigValue<T> implements IConfigValue<T> {
    private final ModConfigSpec.ConfigValue<T> value;

    public NeoConfigValue(ModConfigSpec.ConfigValue<T> value) {
        this.value = value;
    }

    @Override
    public T get() {
        return this.value.get();
    }

    @Override
    public T getDefault() {
        return this.value.getDefault();
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
