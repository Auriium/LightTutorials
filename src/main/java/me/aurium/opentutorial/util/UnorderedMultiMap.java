package me.aurium.opentutorial.util;

import java.util.Collection;
import java.util.HashSet;

public class UnorderedMultiMap<T,V> extends DelegatingMultiMap<T,V> {

    @Override
    public void insert(T t, V v) {
        map.computeIfAbsent(t, k -> new HashSet<>()).add(v);
    }

    @Override
    public void insertAll(T t, Collection<V> v) {
        map.computeIfAbsent(t,k -> new HashSet<>()).addAll(v);
    }
}
