package xyz.auriium.opentutorial.core.stage.age;

import xyz.auriium.opentutorial.core.tutorial.stage.AwaitStage;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Specialist stage that will run a command if input number is below a certain number and optionally cancel
 */
public class AgeStage implements AwaitStage {

    private final String runOnFail;
    private final int belowAge;
    private final Long maxDelay;

    private final boolean isCancelOnFail;

    public AgeStage(String runOnFail, int belowAge, Long maxDelay, boolean isCancelOnFail) {
        this.runOnFail = runOnFail;
        this.belowAge = belowAge;
        this.maxDelay = maxDelay;
        this.isCancelOnFail = isCancelOnFail;
    }

    public Optional<String> getRunOnFail() {
        return Optional.ofNullable(runOnFail);
    }

    public int getBelowAge() {
        return belowAge;
    }

    public Optional<Long> getMaxDelay() {
        return Optional.ofNullable(maxDelay);
    }

    public boolean isCancelOnFail() {
        return isCancelOnFail;
    }
}
