package xyz.auriium.opentutorial.spigot.stage;

import xyz.auriium.opentutorial.core.tutorial.stage.AwaitStage;

import java.util.List;

public class ClickableQuizStage implements AwaitStage {

    private final List<String> options;

    private final int maxDelay;
    private final int correctOption;

    private final String outOfTimeMessage;

    public ClickableQuizStage(List<String> options, int maxDelay, int correctOption, String outOfTimeMessage) {
        this.options = options;
        this.maxDelay = maxDelay;
        this.correctOption = correctOption;
        this.outOfTimeMessage = outOfTimeMessage;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    @Override
    public long getMaxDelay() {
        return maxDelay;
    }

    @Override
    public String getOutOfTimeMessage() {
        return outOfTimeMessage;
    }
}
