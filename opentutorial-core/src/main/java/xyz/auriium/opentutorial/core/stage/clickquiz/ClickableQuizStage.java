package xyz.auriium.opentutorial.core.stage.clickquiz;

import xyz.auriium.opentutorial.core.tutorial.stage.AwaitStage;

import java.util.List;
import java.util.Optional;

public class ClickableQuizStage implements AwaitStage {

    private final List<String> options;
    private final Long maxDelay;
    private final int correctOption;

    private final boolean cancelOnFail;
    private final String commandOnFail;

    public ClickableQuizStage(List<String> options, Long maxDelay, int correctOption, boolean cancelOnFail, String commandOnFail) {
        this.options = options;
        this.maxDelay = maxDelay;
        this.correctOption = correctOption;
        this.cancelOnFail = cancelOnFail;
        this.commandOnFail = commandOnFail;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public Optional<Long> getMaxDelay() {
        return Optional.ofNullable(maxDelay);
    }

    public boolean isCancelOnFail() {
        return cancelOnFail;
    }

    public Optional<String> getCommandOnFail() {
        return Optional.ofNullable(commandOnFail);
    }

}
