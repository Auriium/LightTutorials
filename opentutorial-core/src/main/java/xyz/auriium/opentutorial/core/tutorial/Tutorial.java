package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.opentutorial.core.InternalDependentModule;

import java.util.UUID;

/**
 * Object representing a logic cycling of a tutorial
 */
public interface Tutorial {

    //Tutorial data methods

    /**
     * Get the unique identifier of this tutorial (and player)
     * @return the identifier of what spawned the tutorial
     */
    UUID getIdentifier();
    TutorialStorage localStorage();
    Platform getPlatform();
    InternalDependentModule getModule();

    //Tutorial control methods

    /**
     * Marks current stage as complete and moves to accepting the next one. If all stages are finished the tutorial is marked as complete.
     */
    void fireNext();

    /**
     * Marks tutorial as cancelled and passes marking to all linked consumers.
     */
    void fireCancel();


}
