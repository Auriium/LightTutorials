package xyz.auriium.opentutorial.stage.response;

import xyz.auriium.opentutorial.centralized.registry.Event;

public class ClickableEvent implements Event {

    private final int clicked;

    public ClickableEvent(int clicked) {
        this.clicked = clicked;
    }

    public int getClicked() {
        return clicked;
    }
}
