package xyz.auriium.opentutorial.spigot.stage;

public class ClickableEvent {

    private final int clicked;

    public ClickableEvent(int clicked) {
        this.clicked = clicked;
    }

    public int getClicked() {
        return clicked;
    }
}
