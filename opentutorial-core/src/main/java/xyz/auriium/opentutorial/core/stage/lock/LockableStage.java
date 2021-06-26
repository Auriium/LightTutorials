package xyz.auriium.opentutorial.core.stage.lock;

import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

public class LockableStage implements Stage {

    private final boolean lockMovement;
    private final boolean lockView;

    public LockableStage(boolean lockMovement, boolean lockView) {
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
