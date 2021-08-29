package xyz.auriium.opentutorial.core.types.keyword;

import xyz.auriium.opentutorial.core.consumer.stage.AwaitStage;

import java.util.List;
import java.util.Optional;

/**
 * Stage that awaits the player saying a specific keyword before allowing them to pass. Is timed.
 */
@Deprecated
public class PlainKeywordStage implements AwaitStage {

    private final List<String> matchables;
    private final boolean cancelOnFail;
    private final String commandOnFail;

    private final Integer maxDelay;
    private final String actionbarFormat;

    public PlainKeywordStage(List<String> matchables, boolean cancelOnFail, String commandOnFail, Integer maxDelay, String actionbarFormat) {
        this.matchables = matchables;
        this.cancelOnFail = cancelOnFail;
        this.commandOnFail = commandOnFail;
        this.maxDelay = maxDelay;
        this.actionbarFormat = actionbarFormat;
    }

    public Optional<String> getCommandOnFail() {
        return Optional.ofNullable(commandOnFail);
    }

    public List<String> getMatchables() {
        return matchables;
    }

    public Integer getDelay() {
        return maxDelay;
    }

    @Override
    public String getDelayFormat() {
        return actionbarFormat;
    }

    public boolean isCancelOnFail() {
        return cancelOnFail;
    }

}
