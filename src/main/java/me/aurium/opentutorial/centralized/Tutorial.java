package me.aurium.opentutorial.centralized;

import me.aurium.opentutorial.stage.Stage;

import java.util.Set;
import java.util.UUID;

public interface Tutorial {

    /**
     * Get the unique identifier of this tutorial (and player)
     * @return the identifier of what spawned the tutorial
     */
    UUID getIdentifier();

    /**
     * Gets the identifier of the template that represents this object
     * @return the identifier
     */
    String getTemplateIdentifier();

    void fireNext();
    void fireCancel();

}
