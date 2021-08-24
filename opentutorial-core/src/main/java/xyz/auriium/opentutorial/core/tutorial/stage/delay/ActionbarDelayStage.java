package xyz.auriium.opentutorial.core.tutorial.stage.delay;

import xyz.auriium.opentutorial.core.tutorial.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.Delayable;

/**
 * A new implementation of DelayStage that sends a repeating bar to the player while delayed
 */
public class ActionbarDelayStage implements Stage, Delayable {

    private final int delay;
    private final String actionbarFormat;

    public ActionbarDelayStage(int delay, String actionbarFormat) {
        this.delay = delay;
        this.actionbarFormat = actionbarFormat;
    }

    public Integer getDelay() {
        return delay;
    }

    public String getDelayFormat() {
        return actionbarFormat;
    }
}
