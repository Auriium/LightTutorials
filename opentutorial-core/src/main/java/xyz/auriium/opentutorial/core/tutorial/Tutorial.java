package xyz.auriium.opentutorial.core.tutorial;

import java.util.UUID;

/**
 * Object representing a logic cycling of a tutorial
 */
public interface Tutorial {

    /**
     * Get the unique identifier of this tutorial (and player)
     * @return the identifier of what spawned the tutorial
     */
    UUID getIdentifier();

    TutorialStorage localStorage();

    /**
     * Marks current stage as complete and moves to accepting the next one. If all stages are finished the tutorial is marked as complete.
     */
    void fireNext();

    /**
     * Marks tutorial as cancelled and passes marking to all linked consumers.
     */
    void fireCancel();


}
