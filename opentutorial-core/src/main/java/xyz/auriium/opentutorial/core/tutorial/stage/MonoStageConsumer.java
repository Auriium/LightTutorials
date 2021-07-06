package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.opentutorial.api.construct.Stage;
import xyz.auriium.opentutorial.api.construct.Tutorial;

/**
 * Consumer which yields no control of the tutorial to the implementer
 * @param <T> type
 */
public abstract class MonoStageConsumer<T extends Stage> implements BasicStageConsumer<T> {

    @Override
    public void started(T options, Tutorial continuable) {
        logic(options);

        continuable.fireNext();
    }

    abstract void logic(T options);

}
