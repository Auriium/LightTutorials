package xyz.auriium.opentutorial.stage.await;

import xyz.auriium.beetle.utility.map.optional.DelegatingOptionalMap;
import xyz.auriium.beetle.utility.map.optional.OptionalMap;
import xyz.auriium.opentutorial.PluginScheduler;
import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.centralized.registry.Event;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractDelayConsumer<T extends AwaitStage,E extends Event> implements AwaitConsumer<T,E> {

    private final OptionalMap<UUID,T> existenceCache = new DelegatingOptionalMap<>();
    private final Map<UUID,BukkitTask> delayCache = new HashMap<>();

    private final PluginScheduler scheduler;

    public AbstractDelayConsumer(PluginScheduler scheduler) {
        this.scheduler = scheduler;
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
                        continuable.fireCancel();
                        delayCache.remove(uuid);
                    },
                    options.getMaxDelay()));
        }
    }

    @Override
    public void closeSingle(UUID uuid) {
        existenceCache.remove(uuid);

        BukkitTask task = delayCache.remove(uuid);

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
