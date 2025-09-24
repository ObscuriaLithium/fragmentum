package dev.obscuria.fragmentum.util.event;

@FunctionalInterface
public interface EventHandler<T>
{
    void handle(T listener);
}
