package xyz.auriium.opentutorial.core.tutorial;

import java.util.Queue;
import java.util.UUID;

/**
 * Simply just a template with a UUID attached
 */
public class CommonTutorial implements Tutorial {

    private final UUID uuid;
    private final Queue<Stage> stageQueue;
    private final TutorialController controller;
    private final TutorialStorage storage = new CommonTutorialStorage();

    public CommonTutorial(UUID uuid, Queue<Stage> stageQueue, TutorialController controller) {
        this.uuid = uuid;
        this.stageQueue = stageQueue;
        this.controller = controller;
    }

    @Override
    public UUID getIdentifier() {
        return uuid;
    }

    @Override
    public TutorialStorage localStorage() {
        return storage;
    }

    @Override
    public void fireNext() {

        storage.closeStage();

        if (stageQueue.isEmpty()) {
            stop();
            return;

        }

        controller.consumeStage(stageQueue.remove(), this);
    }

    @Override
    public void fireCancel() {
        stop();
    }

    void stop() {
        storage.closeTutorial();
        controller.cancelByUUID(uuid);
    }
}
