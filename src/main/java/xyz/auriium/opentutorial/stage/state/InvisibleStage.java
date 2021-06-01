package xyz.auriium.opentutorial.stage.state;

import xyz.auriium.opentutorial.stage.Stage;

public class InvisibleStage implements Stage {

    private final boolean on;

    public InvisibleStage(boolean on) {
        this.on = on;
    }

    public boolean isOn() {
        return on;
    }
}
