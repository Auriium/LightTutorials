package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.opentutorial.core.InternalDependentModule;
import xyz.auriium.opentutorial.core.consumer.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;

import java.util.Queue;
import java.util.UUID;

/**
 * Simply just a template with a UUID attached
 */
public class CommonTutorial implements Tutorial {

    private final UUID uuid;
    private final Queue<Stage> stageQueue;
    private final ConsumerCentralizer consumerCentralizer;
    private final Platform platform;
    private final InternalDependentModule module;

    private final TutorialStorage storage = new CommonTutorialStorage();

    public CommonTutorial(UUID uuid, Queue<Stage> stageQueue, ConsumerCentralizer consumerCentralizer, Platform platform, InternalDependentModule module) {
        this.uuid = uuid;
        this.stageQueue = stageQueue;
        this.consumerCentralizer = consumerCentralizer;
        this.platform = platform;
        this.module = module;
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
    public Platform getPlatform() {
        return platform;
    }

    @Override
    public InternalDependentModule getModule() {
        return module;
    }

    @Override
    public void fireNext() {

        storage.closeStage();

        if (stageQueue.isEmpty()) {
            stop();
            return;

        }

        consumerCentralizer.consumeStage(stageQueue.remove(), this);
    }

    @Override
    public void fireCancel() {
        stop();
    }

    void stop() {
        storage.closeTutorial();
        //TODO remove self from map
    }
}
