package xyz.auriium.opentutorial.core.consumer;

import xyz.auriium.opentutorial.core.consumer.EventStageConsumer;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.consumer.stage.StageLocalValue;
import xyz.auriium.opentutorial.core.event.Event;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public interface AwaitConsumer<T extends Stage,E extends Event> extends EventStageConsumer<T,E> {

    @SuppressWarnings("unchecked")
    @Override
    default void eventCalled(E event, Tutorial tutorial) {
        tutorial.localStorage().retrieveOptional("stage").ifPresent(stage -> consume((T) stage.get(), event, tutorial));
    }

    @Override
    default void stageStarted(T options, Tutorial continuable) {
        continuable.localStorage().register("stage", new StageLocalValue<>(options, true, var -> {}));
        started1(options, continuable);
    }

    void started1(T options, Tutorial continuable);


}
