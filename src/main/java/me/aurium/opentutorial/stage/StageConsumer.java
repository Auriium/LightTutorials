package me.aurium.opentutorial.stage;

import me.aurium.beetle.defaults.utility.aspect.UUIDCloseable;
import me.aurium.opentutorial.centralized.Tutorial;

public interface StageConsumer<T extends Stage> extends UUIDCloseable {

    void started(T options, Tutorial continuable);

    Class<T> stageClass();

}
