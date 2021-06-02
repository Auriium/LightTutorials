package xyz.auriium.opentutorial.stage.response;

import xyz.auriium.opentutorial.stage.await.AwaitStage;

import java.util.List;

public class PlainKeywordStage implements AwaitStage {

    private final List<String> matchables;

    private final long maxDelay;
    private final boolean cancelOnFail;

    private final String outOfTimeMessage;

    public PlainKeywordStage(List<String> matchables, long maxDelay, boolean cancelOnFail, String outOfTimeMessage) {
        this.matchables = matchables;
        this.maxDelay = maxDelay;
        this.cancelOnFail = cancelOnFail;
        this.outOfTimeMessage = outOfTimeMessage;
    }

    public List<String> getMatchables() {
        return matchables;
    }

    public long getMaxDelay() {
        return maxDelay;
    }

    @Override
    public String getOutOfTimeMessage() {
        return outOfTimeMessage;
    }

    public boolean isCancelOnFail() {
        return cancelOnFail;
    }

}
