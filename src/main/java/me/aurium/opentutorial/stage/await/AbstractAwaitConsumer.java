package me.aurium.opentutorial.stage.await;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.centralized.registry.Event;
import me.aurium.opentutorial.stage.Stage;
import me.aurium.opentutorial.util.CloseableOptionalMap;

import java.util.UUID;

public abstract class AbstractAwaitConsumer<T extends Stage,E extends Event> implements AwaitConsumer<T,E> {

    /**
     * TODO: watch for memory leaks: this looks like a source for one.
     *
     * (This is an old todo from before this was a delegatingmap.)
     */
    protected final CloseableOptionalMap<UUID,T> cache = new CloseableOptionalMap<>();

    @Override
    public void started(T options, Tutorial continuable) {
        cache.delegate().put(continuable.getIdentifier(),options);
    }

    @Override
    public void consume(E event, Tutorial tutorial) {
        cache.removeIfPresent(tutorial.getIdentifier(), stage -> consume(stage,event,tutorial));
    }

    @Override
    public void closeAll() {
        cache.closeAll();
    }

    @Override
    public void closeSingle(UUID uuid) {
        cache.closeSingle(uuid);
    }
}
