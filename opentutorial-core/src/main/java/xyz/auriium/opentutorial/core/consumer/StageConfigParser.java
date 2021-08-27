package xyz.auriium.opentutorial.core.consumer;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;

import java.util.Map;

public interface StageConfigParser {

    /**
     * The identifier of the stage type
     * @return stage type identifier
     */
    String identifier();


    /**
     * Action representing deserialization
     * @param map the map
     * @return the values
     * @throws BadValueException bad value exception
     */
    Stage deserialize(Map<String, FlexibleType> map) throws BadValueException;

}
