package xyz.auriium.opentutorial.core.config.messages;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.Decomposer;
import space.arim.dazzleconf.serialiser.FlexibleType;
import space.arim.dazzleconf.serialiser.ValueSerialiser;
import xyz.auriium.opentutorial.core.model.Colorer;

public class MessageConfSerializer implements ValueSerialiser<Message> {

    private final Colorer colorer;

    public MessageConfSerializer(Colorer colorer) {
        this.colorer = colorer;
    }

    @Override
    public Class<Message> getTargetClass() {
        return Message.class;
    }

    @Override
    public Message deserialise(FlexibleType flexibleType) throws BadValueException {
        return new Message(colorer, flexibleType.getString());
    }

    @Override
    public Object serialise(Message message, Decomposer decomposer) {
        return message.getTranslatable();
    }
}
