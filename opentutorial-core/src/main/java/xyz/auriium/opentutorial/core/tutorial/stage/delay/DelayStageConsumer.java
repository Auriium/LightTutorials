package xyz.auriium.opentutorial.core.tutorial.stage.delay;

import xyz.auriium.opentutorial.core.platform.Scheduler;
import xyz.auriium.opentutorial.core.platform.SchedulerTask;
import xyz.auriium.opentutorial.core.tutorial.StageLocalValue;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.UUID;

/**
 * Consumer system that handles {@link DelayStage}
 */
public class DelayStageConsumer implements StageConsumer<DelayStage> {

    private final Scheduler scheduler;

    DelayStageConsumer(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void started(DelayStage stage, Tutorial continuable) {
        UUID uuid = continuable.getIdentifier();

        SchedulerTask task = scheduler.runLater(continuable::fireNext, stage.getDelay());
        continuable.localStorage().register("delayTask", new StageLocalValue<>(task, true, val -> task.cancel()));
    }

    @Override
    public Class<DelayStage> stageClass() {
        return DelayStage.class;
    }

}
