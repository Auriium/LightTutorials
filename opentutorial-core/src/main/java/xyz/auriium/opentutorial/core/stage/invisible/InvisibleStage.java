package xyz.auriium.opentutorial.core.stage.invisible;


import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

public class InvisibleStage implements Stage {

    private final boolean on;

    public InvisibleStage(boolean on) {
        this.on = on;
    }

    public boolean isOn() {
        return on;
    }
}
