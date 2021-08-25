package xyz.auriium.opentutorial.core.tutorial.stage.delay;

import xyz.auriium.opentutorial.core.tutorial.StageLocalValue;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.platform.Scheduler;
import xyz.auriium.opentutorial.core.platform.SchedulerTask;
import xyz.auriium.opentutorial.core.platform.Teachable;
import xyz.auriium.opentutorial.core.platform.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.stage.RepeatedRunnable;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.UUID;

public class ActionbarDelayConsumer implements StageConsumer<ActionbarDelayStage> {

    private final Scheduler scheduler;
    private final TeachableRegistry teachableRegistry;

    public ActionbarDelayConsumer(Scheduler scheduler, TeachableRegistry teachableRegistry) {
        this.scheduler = scheduler;
        this.teachableRegistry = teachableRegistry;
    }

    @Override
    public void started(ActionbarDelayStage options, Tutorial continuable) {
        UUID uuid = continuable.getIdentifier();
        Teachable teachable = teachableRegistry.getAudienceByUUID(uuid).orElseThrow(() -> {

           throw new IllegalStateException("No teachable present!");

        });

        SchedulerTask task = scheduler.runRepeated(new RepeatedRunnable(teachable, options, continuable), 2L);
        continuable.localStorage().register("actionbarTask", new StageLocalValue<>(task, true, val -> task.cancel()));
    }

    @Override
    public Class<ActionbarDelayStage> stageClass() {
        return ActionbarDelayStage.class;
    }

}
