package me.aurium.opentutorial.centralized;

import me.aurium.opentutorial.centralized.states.StateMap;

import java.util.UUID;

public interface Tutorial {

    /**
     * Get the unique identifier of this tutorial (and player)
     * @return the identifier of what spawned the tutorial
     */
    UUID getIdentifier();

    void fireNext();
    void fireCancel();


}
