package me.aurium.opentutorial.stage.response;

import me.aurium.opentutorial.stage.await.AwaitStage;

/**
 * Specialist stage that will happen if x or y idc
 */
public class AgeStage implements AwaitStage {

    private final int belowAge;
    private final String runOnFail;

    private final long maxDelay;

    public AgeStage(int belowAge, String runOnFail, long maxDelay) {
        this.belowAge = belowAge;
        this.runOnFail = runOnFail;
        this.maxDelay = maxDelay;
    }

    @Override
    public long getMaxDelay() {
        return 0;
    }
}
