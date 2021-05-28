package me.aurium.opentutorial.stage.await;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.centralized.registry.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * disgusting class with inheritance but who cares i'm lazy and it isn't that hacky
 * @param <T>
 * @param <E>
 */
public abstract class DelayedAwaitConsumer<T extends AwaitStage,E extends Event> extends AbstractAwaitConsumer<T,E> {

    private final Map<UUID,BukkitTask> delayCache = new HashMap<>();
    private final JavaPlugin plugin;

    protected DelayedAwaitConsumer(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void postStarted(T options, Tutorial continuable) {
        UUID uuid = continuable.getIdentifier();

        if (options.getMaxDelay() != -1) {
            delayCache.put(uuid,plugin.getServer().getScheduler().runTaskLater(plugin,
                    () -> {
                        continuable.fireCancel();
                        delayCache.remove(uuid);
                    },
                    options.getMaxDelay()));
        }

        endStarted(options, continuable);
    }

    public abstract void endStarted(T options, Tutorial continuable);

    @Override
    public void closeSingle(UUID uuid) {
        BukkitTask task = delayCache.remove(uuid);

        if (task != null) {
            task.cancel();
        }
    }

    @Override
    public void close() {
        delayCache.forEach((e,s) -> {
            if (s != null) {
                s.cancel();
            }
        });

        delayCache.clear();
    }

}
