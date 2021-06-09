package xyz.auriium.opentutorial.spigot.stage;

import xyz.auriium.opentutorial.core.tutorial.stage.AwaitStage;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

/**
 * Specialist stage that will run a command if input number is below a certain number and optionally cancel
 */
public class AgeStage implements AwaitStage {

    private final String runOnFail;
    private final int belowAge;
    private final long maxDelay;

    private final boolean isCancelOnFail;

    public AgeStage(String runOnFail, int belowAge, long maxDelay, boolean isCancelOnFail) {
        this.runOnFail = runOnFail;
        this.belowAge = belowAge;
        this.maxDelay = maxDelay;
        this.isCancelOnFail = isCancelOnFail;
    }

    public String getRunOnFail() {
        return runOnFail;
    }

    public int getBelowAge() {
        return belowAge;
    }

    public long getMaxDelay() {
        return maxDelay;
    }

    public boolean isCancelOnFail() {
        return isCancelOnFail;
    }
}
