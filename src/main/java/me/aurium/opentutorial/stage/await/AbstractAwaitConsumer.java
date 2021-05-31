package me.aurium.opentutorial.stage.await;

import me.aurium.beetle.defaults.utility.map.optional.DelegatingOptionalMap;
import me.aurium.beetle.defaults.utility.map.optional.OptionalMap;
import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.centralized.registry.Event;
import me.aurium.opentutorial.stage.Stage;

import java.util.UUID;

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
