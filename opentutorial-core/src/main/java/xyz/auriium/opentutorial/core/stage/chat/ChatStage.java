package xyz.auriium.opentutorial.core.stage.chat;

import xyz.auriium.opentutorial.api.construct.Stage;

import java.util.Optional;

/**
 * Stage that sends multiple forms of user-interfacing to a corresponding user.
 * Can display chat, actionbars, and titles, independent of eachother.
 */
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

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public Optional<String> getSubtitle() {
        return Optional.ofNullable(subtitle);
    }
}
