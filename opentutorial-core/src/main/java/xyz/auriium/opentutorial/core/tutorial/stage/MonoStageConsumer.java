package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.opentutorial.core.tutorial.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

/**
 * Consumer which yields no control of the tutorial to the implementer
 * @param <T> type
 */
public abstract class MonoStageConsumer<T extends Stage> implements StageConsumer<T> {

    @Override
    public void started(T options, Tutorial continuable) {
        logic(options);

        continuable.fireNext();
    }

    abstract void logic(T options);

}
