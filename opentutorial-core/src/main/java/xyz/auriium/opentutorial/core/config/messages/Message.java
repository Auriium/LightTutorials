package xyz.auriium.opentutorial.core.config.messages;

import xyz.auriium.opentutorial.core.platform.base.Audience;
import xyz.auriium.opentutorial.core.platform.base.Colorer;

public class Message {

    private final Colorer colorer;

    private final String translatable;

    public Message(Colorer colorer, String translatable) {
        this.colorer = colorer;
        this.translatable = translatable;
    }

    public void send(Audience sender, Object... strings) {
        sender.sendMessage(colorer.color(String.format(translatable,strings)));
    }
    public void send(Audience sender) {
        sender.sendMessage(colorer.color(translatable));
    }

    public String getTranslatable() {
        return translatable;
    }
}
