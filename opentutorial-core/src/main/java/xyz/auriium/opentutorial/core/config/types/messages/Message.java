package xyz.auriium.opentutorial.core.config.types.messages;

import xyz.auriium.opentutorial.core.model.Audience;
import xyz.auriium.opentutorial.core.model.Colorer;

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
