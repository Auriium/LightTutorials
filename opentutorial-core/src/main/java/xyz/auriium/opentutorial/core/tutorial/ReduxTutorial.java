package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.opentutorial.core.InternalDependentModule;

import java.util.UUID;

public class ReduxTutorial implements Tutorial{

    private final UUID uuid;
    private final Platform platform;
    private final InternalDependentModule module;
    private final ReduxTutorialController controller;

    public ReduxTutorial(UUID uuid, Platform platform, InternalDependentModule module, ReduxTutorialController controller) {
        this.uuid = uuid;
        this.platform = platform;
        this.module = module;
        this.controller = controller;
    }


    @Override
    public UUID getIdentifier() {
        return uuid;
    }

    @Override
    public TutorialStorage localStorage() {
        return controller.storage(uuid);
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
        controller.nextSingle(uuid);
    }

    @Override
    public void fireCancel() {
        controller.cancelByUUID(uuid);
    }

}
