package me.aurium.opentutorial.stage.chatstage;

import me.aurium.opentutorial.stage.Stage;

import java.util.Optional;

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

    public Optional<String> getChat() {
        return Optional.ofNullable(chat);
    }

    public Optional<String> getActionbar() {
        return Optional.ofNullable(actionbar);
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
