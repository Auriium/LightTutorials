package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.opentutorial.core.tutorial.StageLocalValue;
import xyz.auriium.opentutorial.core.tutorial.TutorialStorage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class CommonTutorialStorage implements TutorialStorage {

    private final Map<String, StageLocalValue<?>> map = new ConcurrentHashMap<>();

    @Override
    public <T> void register(String key, StageLocalValue<T> bound) {
        if (map.containsKey(key)) throw new IllegalStateException("Map cannot have two of the same keys!");

        map.put(key, bound);
    }

    @Override
    public <T> Optional<StageLocalValue<T>> retrieveOptional(String key) {
        return Optional.ofNullable((StageLocalValue<T>) map.get(key));
    }

    @Override
    public <T> StageLocalValue<T> retrieveOrThrow(String key) {
        var returned = map.get(key);

        if (returned == null) throw new IllegalStateException("Expected localvalue when none was present for key: " + key);

        return (StageLocalValue<T>) returned;
    }

    @Override
    public void registerHandler(String key) {

    }

    public void closeStage() {

        map.entrySet().removeIf(v -> {

            StageLocalValue<?> value = v.getValue();
            boolean isLocal = value.isLocal();

            if (isLocal) {
                value.callCloseHook();
            }

            return isLocal;
        });

    }

    public void closeTutorial() {
        for (StageLocalValue<?> value : map.values()) {
            value.callCloseHook();
        }

        map.clear();
    }
}
