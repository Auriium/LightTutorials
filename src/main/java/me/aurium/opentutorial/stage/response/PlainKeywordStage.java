package me.aurium.opentutorial.stage.response;

import me.aurium.opentutorial.stage.await.AwaitStage;

public class PlainKeywordStage implements AwaitStage {

    private final String[] matchables;

    private final long maxDelay;
    private final boolean cancelOnFail;

    public PlainKeywordStage(String[] matchables, long maxDelay, boolean cancelOnFail) {
        this.matchables = matchables;
        this.maxDelay = maxDelay;
        this.cancelOnFail = cancelOnFail;
    }

    public String[] getMatchables() {
        return matchables;
    }

    public long getMaxDelay() {
        return maxDelay;
    }

    public boolean isCancelOnFail() {
        return cancelOnFail;
    }

}
