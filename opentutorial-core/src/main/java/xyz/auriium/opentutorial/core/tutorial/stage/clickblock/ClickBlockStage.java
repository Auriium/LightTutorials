package xyz.auriium.opentutorial.core.tutorial.stage.clickblock;

import xyz.auriium.opentutorial.core.tutorial.stage.AwaitStage;

/**
 * Stage that waits for a player to click a specific block on the map
 *
 * TODO add elusive world value
 */
public class ClickBlockStage implements AwaitStage {

    private final int x;
    private final int y;
    private final int z;

    private final Integer maxDelay;
    private final String actionbarFormat;

    public ClickBlockStage(int x, int y, int z, Integer maxDelay, String actionbarFormat) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.maxDelay = maxDelay;
        this.actionbarFormat = actionbarFormat;
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
    public Integer getDelay() {
        return maxDelay;
    }

    @Override
    public String getDelayFormat() {
        return actionbarFormat;
    }
}
