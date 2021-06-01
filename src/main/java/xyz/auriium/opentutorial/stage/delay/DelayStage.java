package xyz.auriium.opentutorial.stage.delay;

import xyz.auriium.opentutorial.stage.Stage;

public class DelayStage implements Stage {

    private final long delay;

    public DelayStage(long delay) {
        this.delay = delay;
    }

    public long getDelay() {
        return delay;
    }


}
