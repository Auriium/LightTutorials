package xyz.auriium.opentutorial.core.stage.clickquiz;

import xyz.auriium.opentutorial.core.tutorial.stage.AwaitStage;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

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

    public long getMaxDelay() {
        return maxDelay;
    }

}
