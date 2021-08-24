package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.opentutorial.core.event.Event;
import xyz.auriium.opentutorial.core.tutorial.Stage;
import xyz.auriium.opentutorial.core.tutorial.StageLocalValue;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public interface AwaitConsumer<T extends Stage,E extends Event> extends IAwaitConsumer<T,E> {

    @SuppressWarnings("unchecked")
    @Override
    default void consume(E event, Tutorial tutorial) {
        tutorial.localStorage().retrieveOptional("stage").ifPresent(stage -> consume((T) stage.get(), event, tutorial));
    }

    @Override
    default void started(T options, Tutorial continuable) {
        continuable.localStorage().register("stage", new StageLocalValue<>(options, true, var -> {}));
        started1(options, continuable);
    }

    void started1(T options, Tutorial continuable);

}
