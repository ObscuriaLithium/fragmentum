package dev.obscuria.fragmentum.content.util.event;

@SuppressWarnings("unused")
@FunctionalInterface
public interface EventHandler<T>
{
    void handle(T listener);
}
