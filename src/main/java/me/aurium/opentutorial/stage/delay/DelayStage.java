package me.aurium.opentutorial.stage.delay;

import me.aurium.opentutorial.stage.Stage;

public class DelayStage implements Stage {

    private final long delay;

    public DelayStage(long delay) {
        this.delay = delay;
    }

    public long getDelay() {
        return delay;
    }


}
