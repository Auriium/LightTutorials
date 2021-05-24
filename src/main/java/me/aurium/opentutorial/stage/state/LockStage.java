package me.aurium.opentutorial.stage.state;

public class LockStage {

    private final boolean lockMovement;
    private final boolean lockView;

    public LockStage(boolean lockMovement, boolean lockView) {
        this.lockMovement = lockMovement;
        this.lockView = lockView;
    }
}
