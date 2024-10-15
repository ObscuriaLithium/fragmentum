package dev.obscuria.fragmentum.api.v1.common.event;

@FunctionalInterface
public interface EventHandler<T>
{
    void handle(T listener);
}
