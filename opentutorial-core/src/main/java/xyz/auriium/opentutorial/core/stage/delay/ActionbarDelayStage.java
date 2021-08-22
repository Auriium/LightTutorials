package xyz.auriium.opentutorial.core.stage.delay;

import xyz.auriium.opentutorial.api.construct.Stage;

/**
 * A new implementation of DelayStage that sends a repeating bar to the player while delayed
 */
public class ActionbarDelayStage implements Stage {

    private final int delay;
    private final String actionbarFormat;

    public ActionbarDelayStage(int delay, String actionbarFormat) {
        this.delay = delay;
        this.actionbarFormat = actionbarFormat;
    }

    public int getDelay() {
        return delay;
    }

    public String getActionbarFormat() {
        return actionbarFormat;
    }
}
