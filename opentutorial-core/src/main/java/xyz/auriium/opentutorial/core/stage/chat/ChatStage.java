package xyz.auriium.opentutorial.core.stage.chat;

import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

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
