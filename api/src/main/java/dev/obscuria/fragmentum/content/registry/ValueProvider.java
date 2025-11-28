package dev.obscuria.fragmentum.content.registry;

import java.util.function.Supplier;

@FunctionalInterface
public interface ValueProvider<T> extends Supplier<T> {}
