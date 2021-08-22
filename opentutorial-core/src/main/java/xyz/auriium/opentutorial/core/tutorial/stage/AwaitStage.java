package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.opentutorial.api.construct.Stage;

import java.util.Optional;

/**
 * Represents a stage that can also have a maximum amount of time before it is forcibly canceled
 */
public interface AwaitStage extends Stage {

    /**
     * The longest amount of time possible to be spent on the stage before it is invalid and parenting tutorial must be cancelled
     * @return long describing said value
     */
    Integer getMaxDelay();

    String getActionbarFormat();

}
