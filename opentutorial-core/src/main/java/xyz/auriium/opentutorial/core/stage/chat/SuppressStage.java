package xyz.auriium.opentutorial.core.stage.chat;

import xyz.auriium.opentutorial.api.construct.Stage;

public class SuppressStage implements Stage {

    private final boolean suppressChat;

    public SuppressStage(boolean suppressChat) {
        this.suppressChat = suppressChat;
    }

    public boolean isSuppressChat() {
        return suppressChat;
    }
}
