package me.aurium.opentutorial.stage;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

/**
 * This doesn't have to be typed...?? why is this typed
 * @param <T>
 */
public interface ConsumerSerializer<T extends Stage> {

    String identifier();

    T serialize(FlexibleType type) throws BadValueException;

}
