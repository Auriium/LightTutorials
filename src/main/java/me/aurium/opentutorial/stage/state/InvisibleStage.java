package me.aurium.opentutorial.stage.state;

import me.aurium.opentutorial.stage.Stage;

public class InvisibleStage implements Stage {

    private final boolean on;

    public InvisibleStage(boolean on) {
        this.on = on;
    }

    public boolean isOn() {
        return on;
    }
}
