package dev.obscuria.fragmentum.content.util.event;

@FunctionalInterface
public interface EventHandler<T> {

    void handle(T listener);
}
