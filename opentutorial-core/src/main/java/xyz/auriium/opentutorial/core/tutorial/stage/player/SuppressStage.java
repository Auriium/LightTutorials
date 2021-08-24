package xyz.auriium.opentutorial.core.tutorial.stage.player;

import xyz.auriium.opentutorial.core.tutorial.Stage;

public class SuppressStage implements Stage {

    private final boolean suppressChat;

    public SuppressStage(boolean suppressChat) {
        this.suppressChat = suppressChat;
    }

    public boolean isSuppressChat() {
        return suppressChat;
    }
}
