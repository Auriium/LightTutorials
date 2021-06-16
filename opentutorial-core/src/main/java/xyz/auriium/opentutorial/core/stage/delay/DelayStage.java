package xyz.auriium.opentutorial.core.stage.delay;

import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

/**
 * Simple stage that makes tutorial wait DELAY's worth of ticks before proceeding
 */
public class DelayStage implements Stage {

    private final long delay;

    public DelayStage(long delay) {
        this.delay = delay;
    }

    public long getDelay() {
        return delay;
    }


}
