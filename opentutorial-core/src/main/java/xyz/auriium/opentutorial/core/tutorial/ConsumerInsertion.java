package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

/**
 * Represents an insertion of a stage consumer that is added before insertion
 */
public interface ConsumerInsertion {

    StageConsumer<?> build(Platform platform, ConfigController configController);

}
