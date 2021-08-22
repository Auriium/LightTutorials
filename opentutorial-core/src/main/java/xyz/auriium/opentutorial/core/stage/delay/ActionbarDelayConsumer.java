package xyz.auriium.opentutorial.core.stage.delay;

import xyz.auriium.opentutorial.api.construct.Tutorial;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.SchedulerTask;
import xyz.auriium.opentutorial.core.platform.base.Teachable;
import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActionbarDelayConsumer implements StageConsumer<ActionbarDelayStage> {

    private final Scheduler scheduler;
    private final TeachableRegistry teachableRegistry;
    private final Map<UUID, SchedulerTask> map = new HashMap<>();

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

        map.put(uuid, scheduler.runRepeated(new Runnable() {

            int val = options.getDelay() / 2;

            @Override
            public void run() {

                val--;

                int tens = val / 10;
                int ones = val - (tens * 10);


                String format = options.getActionbarFormat();
                if (format != null) {
                    teachable.sendActionbar(String.format(format, tens, ones));
                }

                if (val <= 0) {
                    map.remove(uuid).cancel();

                    continuable.fireNext();
                }
            }
        }, 2L));
    }

    @Override
    public Class<ActionbarDelayStage> stageClass() {
        return ActionbarDelayStage.class;
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
