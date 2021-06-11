package xyz.auriium.opentutorial.spigot.stage;

import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

public class DelayStage implements Stage {

    private final long delay;

    public DelayStage(long delay) {
        this.delay = delay;
    }

    public long getDelay() {
        return delay;
    }


}
