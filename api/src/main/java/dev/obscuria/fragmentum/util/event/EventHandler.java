package dev.obscuria.fragmentum.util.event;

@SuppressWarnings("unused")
@FunctionalInterface
public interface EventHandler<T>
{
    void handle(T listener);
}
