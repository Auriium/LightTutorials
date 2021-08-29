package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.opentutorial.core.consumer.stage.Stage;

import java.util.Queue;

public class ReduxData {

    private final Queue<Stage> stageQueue;
    private final Tutorial tutorial;
    private final TutorialStorage storage = new CommonTutorialStorage();

    public ReduxData(Queue<Stage> stageQueue, Tutorial tutorial) {
        this.stageQueue = stageQueue;
        this.tutorial = tutorial;
    }

    public Queue<Stage> getStageQueue() {
        return stageQueue;
    }

    public Tutorial getTutorial() {
        return tutorial;
    }

    public TutorialStorage getStorage() {
        return storage;
    }
}
