package xyz.auriium.opentutorial.stage.spigot;

import xyz.auriium.opentutorial.stage.await.AwaitStage;

public class ClickBlockStage implements AwaitStage {

    private final int x;
    private final int y;
    private final int z;

    private final long maxDelay;
    private final String outOfTimeMessage;

    public ClickBlockStage(int x, int y, int z, long maxDelay, String outOfTimeMessage) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.maxDelay = maxDelay;
        this.outOfTimeMessage = outOfTimeMessage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
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
