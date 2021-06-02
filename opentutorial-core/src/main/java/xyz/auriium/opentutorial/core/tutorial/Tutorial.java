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

    void fireNext();
    void fireCancel();


}
