package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.beetle.utility.map.optional.DelegatingOptionalMap;
import xyz.auriium.beetle.utility.map.optional.OptionalMap;
import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.event.Event;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.SchedulerTask;
import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.api.construct.Tutorial;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * An await consumer that handles AwaitStages - Basically just an {@link AbstractAwaitConsumer} with a time limit.
 * @param <T> await stage to handle
 * @param <E> event to handle
 */
public abstract class AbstractDelayConsumer<T extends AwaitStage,E extends Event> implements AwaitConsumer<T,E> {

    private final OptionalMap<UUID,T> existenceCache = new DelegatingOptionalMap<>();
    private final Map<UUID, SchedulerTask> delayCache = new HashMap<>();

    private final Scheduler scheduler;
    protected final TeachableRegistry registry;
    protected final MessageConfig config;

    protected AbstractDelayConsumer(Scheduler scheduler, TeachableRegistry registry, MessageConfig config) {
        this.scheduler = scheduler;
        this.registry = registry;
        this.config = config;
    }

    @Override
    public void consume(E event, Tutorial tutorial) {
        existenceCache.removeIfPresent(tutorial.getIdentifier(), stage -> consume(stage,event,tutorial));
    }

    @Override
    public void started(T options, Tutorial continuable) {
        existenceCache.delegate().put(continuable.getIdentifier(),options);

        UUID uuid = continuable.getIdentifier();

        options.getMaxDelay().ifPresent(aLong ->
                delayCache.put(uuid,scheduler.runLater(
                () -> {
                    delayCache.remove(uuid).cancel();
                    registry.getAudienceByUUID(uuid).ifPresent(audience -> config.outOfTimeMessage().send(audience));
                    continuable.fireCancel();
                },
                aLong)));
    }



    @Override
    public void closeSingle(UUID uuid) {
        existenceCache.remove(uuid);

        SchedulerTask task = delayCache.remove(uuid);

        if (task != null) {
            task.cancel();
        }
    }

    @Override
    public void close() {
        existenceCache.delegate().clear();

        delayCache.forEach((e,s) -> {
            if (s != null) {
                s.cancel();
            }
        });

        delayCache.clear();
    }
}
