package xyz.auriium.opentutorial.core.config;

import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;

public class Message {

    private final Colorer colorer;
    private final String translatable;

    public Message(Colorer colorer, String translatable) {
        this.colorer = colorer;
        this.translatable = translatable;
    }

    public void send(Interfaceable sender, Object... strings) {

        if (!translatable.equals("none")) {
            sender.sendString(String.format(colorer.color(translatable),strings));
        }

    }
    public void send(Interfaceable sender) {
        send(sender,"");
    }

    public String getTranslatable() {
        return translatable;
    }

    public String parse(Object... strings) {
        return String.format(colorer.color(translatable),strings);
    }
}
