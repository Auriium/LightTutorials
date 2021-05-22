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
    private final CloseableOptionalMap<UUID,T> existenceCache = new CloseableOptionalMap<>();

    @Override
    public void started(T options, Tutorial continuable) {
        existenceCache.delegate().put(continuable.getIdentifier(),options);

        postStarted(options, continuable);
    }

    abstract void postStarted(T options, Tutorial continuable);

    @Override
    public void consume(E event, Tutorial tutorial) {
        existenceCache.removeIfPresent(tutorial.getIdentifier(), stage -> consume(stage,event,tutorial));
    }

    @Override
    public void closeAll() {
        existenceCache.closeAll();
    }

    @Override
    public void closeSingle(UUID uuid) {
        existenceCache.closeSingle(uuid);
    }
}
