package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

/**
 * Represents an insertion of a stage consumer that is added before insertion
 */
public interface ConsumerInsertion {

    /**
     * Builds a stage consumer using resources provided
     * @param platform the platform
     * @param configController the controller
     * @return a new stageconsumer per reload
     */
    StageConsumer<?> build(Platform platform, ConfigController configController);

}
