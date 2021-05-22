package me.aurium.opentutorial.util;

import java.util.*;
import java.util.function.Consumer;

public abstract class DelegatingMultiMap<T,V> implements MultiMap<T,V> {

    protected final Map<T, Collection<V>> map;

    public DelegatingMultiMap(Map<T, Collection<V>> map) {
        this.map = map;
    }

    public DelegatingMultiMap() {
        this.map = new HashMap<>();
    }

    @Override
    public Map<T, Collection<V>> getDelegate() {
        return map;
    }

    @Override
    public Collection<V> get(T key) {
        return map.get(key);
    }

    @Override
    public Collection<V> remove(T key) {
        return map.remove(key);
    }

    @Override
    public void replaceAll(T t, Collection<V> v) {
        map.put(t,v);
    }

    @Override
    public void forEach(T key, Consumer<V> consumer) {
        Collection<V> col = map.get(key);

        if (col != null) {
            for(V v : col) {
                consumer.accept(v);
            }
        }
    }

    @Override
    public void forAll(T key, Consumer<Collection<V>> consumer) {
        Collection<V> col = map.get(key);

        if (col != null) {
            consumer.accept(col);
        }
    }
}
