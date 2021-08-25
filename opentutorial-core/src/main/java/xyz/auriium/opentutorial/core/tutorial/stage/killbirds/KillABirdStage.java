package xyz.auriium.opentutorial.core.tutorial.stage.killbirds;

import xyz.auriium.opentutorial.core.tutorial.Stage;

public class KillABirdStage implements Stage {

    private final int birdsToKill;

    public KillABirdStage(int birdsToKill) {
        this.birdsToKill = birdsToKill;
    }

    public int getBirdsToKill() {
        return birdsToKill;
    }
}
