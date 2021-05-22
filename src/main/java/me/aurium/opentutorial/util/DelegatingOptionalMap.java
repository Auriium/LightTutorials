package me.aurium.opentutorial.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class DelegatingOptionalMap<T,E> implements OptionalMap<T,E> {

    protected final Map<T,E> delegate;

    public DelegatingOptionalMap(Map<T, E> delegate) {
        this.delegate = delegate;
    }

    public DelegatingOptionalMap() {
        this.delegate = new HashMap<>();
    }

    @Override
    public Optional<E> remove(T t) {
        return Optional.ofNullable(delegate.remove(t));
    }

    @Override
    public Optional<E> get(T t) {
        return Optional.ofNullable(delegate.get(t));
    }

    @Override
    public void removeIfPresent(T key, Consumer<E> consumer) {
        E e = delegate.remove(key);

        if (e != null) {
            consumer.accept(e);
        }
    }

    @Override
    public void consumeIfPresent(T key, Consumer<E> consumer) {
        E e = delegate.get(key);

        if (e != null) {
            consumer.accept(e);
        }
    }

    @Override
    public Map<T, E> delegate() {
        return delegate;
    }
}
