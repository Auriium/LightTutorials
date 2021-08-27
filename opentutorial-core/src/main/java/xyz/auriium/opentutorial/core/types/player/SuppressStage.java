package xyz.auriium.opentutorial.core.types.player;

import xyz.auriium.opentutorial.core.consumer.stage.Stage;

public class SuppressStage implements Stage {

    private final boolean suppressChat;

    public SuppressStage(boolean suppressChat) {
        this.suppressChat = suppressChat;
    }

    public boolean isSuppressChat() {
        return suppressChat;
    }
}
