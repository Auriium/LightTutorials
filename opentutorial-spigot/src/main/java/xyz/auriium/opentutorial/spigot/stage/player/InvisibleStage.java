package xyz.auriium.opentutorial.spigot.stage.player;


import xyz.auriium.opentutorial.core.consumer.stage.Stage;

/*
 * Stage that marks the player as invisible to the server
 */
public class InvisibleStage implements Stage {

    private final boolean on;

    public InvisibleStage(boolean on) {
        this.on = on;
    }

    public boolean isOn() {
        return on;
    }
}
