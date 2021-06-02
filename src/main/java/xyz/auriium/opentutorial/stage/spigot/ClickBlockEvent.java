package xyz.auriium.opentutorial.stage.spigot;

import xyz.auriium.opentutorial.centralized.registry.Event;
import xyz.auriium.opentutorial.stage.await.AwaitStage;

public class ClickBlockEvent implements Event {

    private final int x;
    private final int y;
    private final int z;

    public ClickBlockEvent(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
}
