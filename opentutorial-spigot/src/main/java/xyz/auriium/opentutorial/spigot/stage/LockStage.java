package xyz.auriium.opentutorial.spigot.stage;

import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

public class LockStage implements Stage {

    private final boolean lockMovement;
    private final boolean lockView;

    public LockStage(boolean lockMovement, boolean lockView) {
        this.lockMovement = lockMovement;
        this.lockView = lockView;
    }

    public boolean isLockMovement() {
        return lockMovement;
    }

    public boolean isLockView() {
        return lockView;
    }
}
