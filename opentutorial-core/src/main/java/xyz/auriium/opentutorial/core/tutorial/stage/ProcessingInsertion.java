package xyz.auriium.opentutorial.core.tutorial.stage;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.api.construct.Stage;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.platform.Platform;

import java.util.Map;


public interface ProcessingInsertion {

    String identifier();

    Stage deserialize(Map<String, FlexibleType> map) throws BadValueException;

    /**
     * Builds a stage consumer using resources provided
     * @param platform the platform
     * @param configController the controller
     * @return a new stageconsumer per reload
     */
    StageConsumer<?> build(Platform<?> platform, ConfigController configController);

}
