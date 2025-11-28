package dev.obscuria.fragmentum.config;

import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("unused")
public final class ConfigValue<T> {

    private ConfigValue() {}

    public T get() {
        throw new NotImplementedException();
    }

    public T getDefault() {
        throw new NotImplementedException();
    }

    public void set(T value) {
        throw new NotImplementedException();
    }

    public void save() {
        throw new NotImplementedException();
    }
}
