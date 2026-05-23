package dev.obscuria.fragmentum.v2.api.common.registry;

import java.util.function.Supplier;

@FunctionalInterface
public interface ValueProvider<T> extends Supplier<T> {}
