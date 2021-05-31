package me.aurium.opentutorial.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MultiLinkMap<T,K,V> {

    private final Map<T, Map<K,V>> map = new HashMap<>();

    public void put(T t, K k, V v) {
        map.computeIfAbsent(t, a -> new HashMap<>()).put(k,v);
    }

    public Optional<V> get(T t, K k) {
        return Optional.ofNullable(map.computeIfAbsent(t, a -> new HashMap<>()).get(k));
    }

    public Map<T, Map<K,V>> delegate() {
        return map;
    }


}
