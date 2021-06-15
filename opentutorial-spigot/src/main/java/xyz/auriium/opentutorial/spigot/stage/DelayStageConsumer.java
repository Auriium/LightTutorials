package xyz.auriium.opentutorial.spigot.stage;

import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.SchedulerTask;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DelayStageConsumer implements StageConsumer<DelayStage> {

    private final Scheduler scheduler;

    private final Map<UUID, SchedulerTask> map = new HashMap<>();

    public DelayStageConsumer(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void started(DelayStage stage, Tutorial continuable) {
        UUID uuid = continuable.getIdentifier();

        map.put(uuid, scheduler.runLater( () -> {
            map.remove(uuid).cancel();

            continuable.fireNext();
        }, stage.getDelay()));
    }

    @Override
    public Class<DelayStage> stageClass() {
        return DelayStage.class;
    }

    @Override
    public void closeSingle(UUID uuid) {

        SchedulerTask task = map.remove(uuid);

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
