package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.beetle.utility.map.optional.DelegatingOptionalMap;
import xyz.auriium.beetle.utility.map.optional.OptionalMap;
import xyz.auriium.opentutorial.core.event.Event;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.UUID;

/**
 * Abstract implementation of an AwaitConsumer
 * @param <T> stage
 * @param <E> event
 */
public abstract class AbstractAwaitConsumer<T extends Stage,E extends Event> implements AwaitConsumer<T,E> {

    private final OptionalMap<UUID,T> existenceCache = new DelegatingOptionalMap<>();

    @Override
    public void consume(E event, Tutorial tutorial) {
        existenceCache.removeIfPresent(tutorial.getIdentifier(), stage -> consume(stage,event,tutorial));
    }

    @Override
    public void started(T options, Tutorial continuable) {
        existenceCache.delegate().put(continuable.getIdentifier(),options);
    }

    @Override
    public void closeSingle(UUID uuid) {
        existenceCache.remove(uuid);
    }

    @Override
    public void close() {
        existenceCache.delegate().clear();
    }
}
