package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.platform.Scheduler;
import xyz.auriium.opentutorial.core.platform.SchedulerTask;
import xyz.auriium.opentutorial.core.platform.Teachable;
import xyz.auriium.opentutorial.core.platform.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.StageLocalValue;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.UUID;

public abstract class DelayConsumer<T extends AwaitStage,E> implements IAwaitConsumer<T,E> {

    protected final TeachableRegistry registry;
    protected final Scheduler scheduler;
    protected final MessageConfig config;

    protected DelayConsumer(TeachableRegistry registry, Scheduler scheduler, MessageConfig config) {
        this.registry = registry;
        this.scheduler = scheduler;
        this.config = config;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void consume(E event, Tutorial tutorial) {
        tutorial.localStorage().retrieveOptional("stage").ifPresent(stage -> consume((T) stage.get(), event, tutorial));
    }

    @Override
    public void started(T options, Tutorial continuable) {
        UUID uuid = continuable.getIdentifier();
        Teachable teachable = registry.getAudienceByUUID(uuid).orElseThrow(() -> {

            throw new IllegalStateException("No teachable present!");

        });


        continuable.localStorage().register("stage", new StageLocalValue<>(options, true, var -> {}));

        if (options.getDelay() != null) {
            SchedulerTask task = scheduler.runRepeated(new MessageRunnable(teachable, options, continuable, config), 2L);
            continuable.localStorage().register("delayconsumer_timer", new StageLocalValue<>(task, true, SchedulerTask::cancel));
        }

        started1(options, continuable);
    }

    protected abstract void started1(T options, Tutorial continuable);

}
