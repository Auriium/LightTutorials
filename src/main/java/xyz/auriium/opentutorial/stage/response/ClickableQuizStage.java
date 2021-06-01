package xyz.auriium.opentutorial.stage.response;

import xyz.auriium.opentutorial.stage.await.AwaitStage;

import java.util.List;

public class ClickableQuizStage implements AwaitStage {

    private final List<String> options;

    private final int maxDelay;
    private final int correctOption;

    public ClickableQuizStage(List<String> options, int maxDelay, int correctOption) {
        this.options = options;
        this.maxDelay = maxDelay;
        this.correctOption = correctOption;
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
}
