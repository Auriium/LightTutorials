package xyz.auriium.opentutorial.core.tutorial.stage;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

import java.util.Map;

/**
 * This doesn't have to be typed...?? why is this typed
 */
public interface StageInsertion {

    String identifier();

    Stage deserialize(Map<String, FlexibleType> map) throws BadValueException;

}
