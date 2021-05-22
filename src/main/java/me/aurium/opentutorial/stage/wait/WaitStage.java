package me.aurium.opentutorial.stage.wait;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.Stage;

public class WaitStage implements Stage {

    private final long delay;

    public WaitStage(long delay) {
        this.delay = delay;
    }

    public long getDelay() {
        return delay;
    }


}
