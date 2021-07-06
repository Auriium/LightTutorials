package xyz.auriium.opentutorial.core.stage.plainkeyword;

import xyz.auriium.opentutorial.core.tutorial.stage.AwaitStage;

import java.util.List;
import java.util.Optional;

/**
 * Stage that awaits the player saying a specific keyword before allowing them to pass. Is timed.
 */
@Deprecated
public class PlainKeywordStage implements AwaitStage {

    private final List<String> matchables;

    private final Long maxDelay;
    private final boolean cancelOnFail;

    private final String commandOnFail;

    public PlainKeywordStage(List<String> matchables, Long maxDelay, boolean cancelOnFail, String commandOnFail) {
        this.matchables = matchables;
        this.maxDelay = maxDelay;
        this.cancelOnFail = cancelOnFail;
        this.commandOnFail = commandOnFail;
    }

    public Optional<String> getCommandOnFail() {
        return Optional.ofNullable(commandOnFail);
    }

    public List<String> getMatchables() {
        return matchables;
    }

    public Optional<Long> getMaxDelay() {
        return Optional.ofNullable(maxDelay);
    }

    public boolean isCancelOnFail() {
        return cancelOnFail;
    }

}
