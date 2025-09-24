package dev.obscuria.fragmentum.registry;

import java.util.function.Supplier;

@SuppressWarnings("unused")
@FunctionalInterface
public interface ValueProvider<T> extends Supplier<T> {}
