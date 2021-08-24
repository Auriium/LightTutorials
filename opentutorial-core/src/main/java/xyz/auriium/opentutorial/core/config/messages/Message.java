package xyz.auriium.opentutorial.core.config.messages;

import xyz.auriium.opentutorial.core.platform.Colorer;
import xyz.auriium.opentutorial.core.platform.Teachable;

public class Message {

    private final Colorer colorer;

    private final String translatable;

    public Message(Colorer colorer, String translatable) {
        this.colorer = colorer;
        this.translatable = translatable;
    }

    public void send(Teachable sender, Object... strings) {

        if (!translatable.equals("none")) {
            sender.sendMessage(String.format(colorer.color(translatable),strings));
        }

    }
    public void send(Teachable sender) {
        send(sender,"");
    }

    public String getTranslatable() {
        return translatable;
    }

    public String parse(Object... strings) {
        return String.format(colorer.color(translatable),strings);
    }
}
