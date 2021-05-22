package me.aurium.opentutorial.stage;

import me.aurium.opentutorial.aspect.Startable;

import java.util.Queue;

public class StandardTutorial implements Continuable, Startable {

    private final Queue<StageConsumer> stageConsumers;

    public StandardTutorial(Queue<StageConsumer> stageConsumers) {
        this.stageConsumers = stageConsumers;
    }

    @Override
    public void markContinue() {
        stageConsumers.remove().started();
    }

    @Override
    public void start() {
        markContinue();
    }
}
