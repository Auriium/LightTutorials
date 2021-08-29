package xyz.auriium.opentutorial.core.consumer;

import xyz.auriium.openmineplatform.api.interfaceable.user.User;
import xyz.auriium.openmineplatform.api.scheduling.SchedulerTask;
import xyz.auriium.opentutorial.core.StageExceptionMapper;
import xyz.auriium.opentutorial.core.config.MessageConfig;
import xyz.auriium.opentutorial.core.consumer.stage.AwaitStage;
import xyz.auriium.opentutorial.core.consumer.stage.StageLocalValue;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public abstract class AbstractDelayConsumer<T extends AwaitStage,E> implements EventStageConsumer<T,E> {

    @SuppressWarnings("unchecked")
    @Override
    public void eventCalled(E event, Tutorial tutorial) {
        tutorial.localStorage().retrieveOptional("stage").ifPresent(stage -> consume((T) stage.get(), event, tutorial));
    }

    @Override
    public void stageStarted(T options, Tutorial continuable) {
        User user = continuable.getPlatform()
                .interRegistry()
                .getTelescoping(continuable.getIdentifier(), StageExceptionMapper.USER);

        continuable.localStorage().register("stage", new StageLocalValue<>(options, true, var -> {}));

        if (options.getDelay() != null) {
            MessageConfig config = continuable.getModule().configController().getMessageConfig();

            SchedulerTask task = continuable.getPlatform().scheduler().runRepeated(new MessageRunnable(user, options, continuable, config), 2L);
            continuable.localStorage().register("delayconsumer_timer", new StageLocalValue<>(task, true, SchedulerTask::cancel));
        }

        started1(options, continuable);

    }
}
