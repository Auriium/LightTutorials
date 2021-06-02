package xyz.auriium.opentutorial.core.tutorial.stage;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

import java.util.Map;

/**
 * This doesn't have to be typed...?? why is this typed
 * @param <T>
 */
public interface StageSerializer<T extends Stage> {

    String identifier();

    T deserialize(Map<String, FlexibleType> map) throws BadValueException;

}
