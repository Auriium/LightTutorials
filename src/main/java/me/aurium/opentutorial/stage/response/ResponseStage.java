package me.aurium.opentutorial.stage.response;

import me.aurium.opentutorial.stage.Stage;

public class ResponseStage implements Stage {

    private final String[] matchables;
    private final int maxDelay;
    private final boolean cancelOnFail;

    public ResponseStage(String[] matchables, int maxDelay, boolean cancelOnFail) {
        this.matchables = matchables;
        this.maxDelay = maxDelay;
        this.cancelOnFail = cancelOnFail;
    }

    public String[] getMatchables() {
        return matchables;
    }

    public int getMaxDelay() {
        return maxDelay;
    }

    public boolean isCancelOnFail() {
        return cancelOnFail;
    }

}
