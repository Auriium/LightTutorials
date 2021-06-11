package xyz.auriium.opentutorial.spigot.stage;

import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

public class ChatStage implements Stage {

    private final String chat;
    private final String actionbar;
    private final String title;
    private final String subtitle;

    public ChatStage(String chat, String actionbar, String title, String subtitle) {
        this.chat = chat;
        this.actionbar = actionbar;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getChat() {
        return chat;
    }

    public String getActionbar() {
        return actionbar;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
