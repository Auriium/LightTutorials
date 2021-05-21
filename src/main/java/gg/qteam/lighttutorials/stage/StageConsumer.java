package gg.qteam.lighttutorials.stage;

import gg.qteam.lighttutorials.aspect.Destructable;
import gg.qteam.lighttutorials.model.SequentialTutorial;

import java.util.UUID;

public interface StageConsumer<T extends Stage> extends Destructable {

    void started(T options, SequentialTutorial continuable);

    Class<T> stageClass();

    @Override
    default void closeAll() {

    }

    @Override
    default void closeSingle(UUID uuid) {

    }
}
