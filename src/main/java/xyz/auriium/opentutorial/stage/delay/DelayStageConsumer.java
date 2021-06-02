package xyz.auriium.opentutorial.stage.delay;

import xyz.auriium.opentutorial.PluginScheduler;
import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.stage.StageConsumer;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DelayStageConsumer implements StageConsumer<DelayStage> {

    private final PluginScheduler scheduler;

    private final Map<UUID, BukkitTask> map = new HashMap<>();

    public DelayStageConsumer(PluginScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void started(DelayStage stage, Tutorial continuable) {
        UUID uuid = continuable.getIdentifier();

        map.put(uuid, scheduler.runLater( () -> {
            continuable.fireNext();

            map.remove(uuid).cancel();
        }, stage.getDelay()));
    }

    @Override
    public Class<DelayStage> stageClass() {
        return DelayStage.class;
    }

    @Override
    public void closeSingle(UUID uuid) {

        BukkitTask task = map.remove(uuid);

        if (task != null) {
            task.cancel();
        }

    }

    @Override
    public void close() {
        map.forEach((e,s) -> {
            if (s != null) {
                s.cancel();
            }
        });

        map.clear();
    }
}
