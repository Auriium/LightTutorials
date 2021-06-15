package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.beetle.utility.map.optional.DelegatingOptionalMap;
import xyz.auriium.beetle.utility.map.optional.OptionalMap;
import xyz.auriium.opentutorial.core.platform.base.AudienceRegistry;
import xyz.auriium.opentutorial.core.config.ConfigHolder;
import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.SchedulerTask;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractDelayConsumer<T extends AwaitStage,E> implements AwaitConsumer<T,E> {

    private final OptionalMap<UUID,T> existenceCache = new DelegatingOptionalMap<>();
    private final Map<UUID, SchedulerTask> delayCache = new HashMap<>();

    private final Scheduler scheduler;
    protected final AudienceRegistry registry;
    protected final ConfigHolder<MessageConfig> config;

    protected AbstractDelayConsumer(Scheduler scheduler, AudienceRegistry registry, ConfigHolder<MessageConfig> config) {
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

        if (options.getMaxDelay() != -1) {
            delayCache.put(uuid,scheduler.runLater(
                    () -> {
                        delayCache.remove(uuid).cancel();
                        registry.getAudienceByUUID(uuid).ifPresent(audience -> config.get().outOfTimeMessage().send(audience));
                        continuable.fireCancel();
                    },
                    options.getMaxDelay()));
        }
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
