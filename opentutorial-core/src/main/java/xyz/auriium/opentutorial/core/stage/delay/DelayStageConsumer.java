package xyz.auriium.opentutorial.core.stage.delay;

import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.SchedulerTask;
import xyz.auriium.opentutorial.api.construct.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Consumer system that handles {@link DelayStage}
 */
public class DelayStageConsumer implements StageConsumer<DelayStage> {

    private final Scheduler scheduler;

    private final Map<UUID, SchedulerTask> map = new HashMap<>(); //hacky and bad

    DelayStageConsumer(Scheduler scheduler) {
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
