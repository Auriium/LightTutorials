package me.aurium.opentutorial.stage.response;

import me.aurium.opentutorial.stage.await.AwaitStage;

import java.util.List;

public class QuizStage implements AwaitStage {

    private final List<String> options;

    private final int maxDelay;
    private final int correctOption;

    public QuizStage(List<String> options, int maxDelay, int correctOption) {
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
