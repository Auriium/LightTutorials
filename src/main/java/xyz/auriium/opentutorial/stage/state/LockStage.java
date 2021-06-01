package xyz.auriium.opentutorial.stage.state;

import xyz.auriium.opentutorial.stage.Stage;

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
