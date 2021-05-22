package me.aurium.opentutorial.centralized;

import me.aurium.opentutorial.stage.Stage;

import java.util.Queue;
import java.util.UUID;

public class CommonTutorial implements Tutorial {

    private final String tutorialModel;
    private final UUID uuid;

    private final Queue<Stage> stageQueue;

    private final TutorialController controller;

    public CommonTutorial(String tutorialModel, UUID uuid, Queue<Stage> stageQueue, TutorialController controller) {
        this.tutorialModel = tutorialModel;
        this.uuid = uuid;
        this.stageQueue = stageQueue;
        this.controller = controller;
    }

    @Override
    public UUID getIdentifier() {
        return uuid;
    }

    @Override
    public String getTemplateIdentifier() {
        return null;
    }

    @Override
    public Stage getCurrentStage() {
        return stageQueue.peek();
    }

    @Override
    public void fireNext() {
        //TODO if the queue is empty, the tutorial is over

        if (stageQueue.isEmpty()) {
            //cum all over the floor uwu
        }

        controller.getRegistry().consumeStage(stageQueue.remove());
    }

    @Override
    public void fireCancel() {

    }
}
