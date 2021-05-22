package me.aurium.opentutorial.stage;

import java.util.UUID;

public interface BasicStageConsumer<T extends Stage> extends StageConsumer<T> {

    @Override
    default void closeAll() {

    }

    @Override
    default void closeSingle(UUID uuid) {

    }
}
