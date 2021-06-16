package xyz.auriium.opentutorial.core.stage.invisible;


import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

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
