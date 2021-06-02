package xyz.auriium.opentutorial.core.config.types.messages;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.Decomposer;
import space.arim.dazzleconf.serialiser.FlexibleType;
import space.arim.dazzleconf.serialiser.ValueSerialiser;

public class MessageConfSerializer implements ValueSerialiser<Message> {
    @Override
    public Class<Message> getTargetClass() {
        return Message.class;
    }

    @Override
    public Message deserialise(FlexibleType flexibleType) throws BadValueException {
        return new Message(flexibleType.getString());
    }

    @Override
    public Object serialise(Message message, Decomposer decomposer) {
        throw new UnsupportedOperationException("no point until dazzleconf adds defaultvals!");
    }
}
