package xyz.auriium.opentutorial.spigot.stage;

import xyz.auriium.opentutorial.core.tutorial.stage.AwaitStage;

/**
 * Specialist stage that will run a command if input number is below a certain number and optionally cancel
 */
public class AgeStage implements AwaitStage {

    private final String runOnFail;
    private final String notNumberMessage;
    private final String outOfTimeMessage;

    private final int belowAge;
    private final long maxDelay;

    private final boolean isCancelOnFail;

    public AgeStage(String runOnFail, String notNumberMessage, String outOfTimeMessage, int belowAge, long maxDelay, boolean isCancelOnFail) {
        this.runOnFail = runOnFail;
        this.notNumberMessage = notNumberMessage;
        this.outOfTimeMessage = outOfTimeMessage;
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

    @Override
    public long getMaxDelay() {
        return maxDelay;
    }

    @Override
    public String getOutOfTimeMessage() {
        return outOfTimeMessage;
    }

    public String getNotNumberMessage() {
        return notNumberMessage;
    }

    public boolean isCancelOnFail() {
        return isCancelOnFail;
    }
}
