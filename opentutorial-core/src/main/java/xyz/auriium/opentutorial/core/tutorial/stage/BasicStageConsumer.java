package xyz.auriium.opentutorial.core.tutorial.stage;

import java.util.UUID;

public interface BasicStageConsumer<T extends Stage> extends StageConsumer<T> {

    @Override
    default void close() {

    }

    @Override
    default void closeSingle(UUID uuid) {

    }
}
