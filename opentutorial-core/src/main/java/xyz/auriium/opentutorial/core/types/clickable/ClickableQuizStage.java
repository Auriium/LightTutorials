package xyz.auriium.opentutorial.core.types.clickable;

import xyz.auriium.opentutorial.core.consumer.stage.AwaitStage;

import java.util.List;
import java.util.Optional;

public class ClickableQuizStage implements AwaitStage {

    private final List<String> options;
    private final int correctOption;

    private final boolean cancelOnFail;
    private final String commandOnFail;

    private final Integer maxDelay;
    private final String actionbarFormat;

    public ClickableQuizStage(List<String> options, int correctOption, boolean cancelOnFail, String commandOnFail, Integer maxDelay, String actionbarFormat) {
        this.options = options;
        this.correctOption = correctOption;
        this.cancelOnFail = cancelOnFail;
        this.commandOnFail = commandOnFail;
        this.maxDelay = maxDelay;
        this.actionbarFormat = actionbarFormat;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
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

    public Optional<String> getCommandOnFail() {
        return Optional.ofNullable(commandOnFail);
    }

}
