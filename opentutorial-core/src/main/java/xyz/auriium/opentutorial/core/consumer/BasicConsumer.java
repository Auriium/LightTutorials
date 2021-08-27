package xyz.auriium.opentutorial.core.consumer;

import xyz.auriium.opentutorial.core.consumer.StageConsumer;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public interface BasicConsumer<T extends Stage> extends StageConsumer<T,Object> {

    @Override
    default void eventCalled(Object event, Tutorial tutorial) {
        throw new UnsupportedOperationException("BasicConsumer should not listen for stages!");
    }

    @Override
    default Class<Object> eventClass() {
        throw new UnsupportedOperationException("BasicConsumer should not listen for stages!");
    }

    @Override
    default boolean isRegisterable() {
        return false;
    }
}
