package dev.obscuria.fragmentum.registry;

import java.util.function.Supplier;

@FunctionalInterface
public interface ValueProvider<T> extends Supplier<T> {}
