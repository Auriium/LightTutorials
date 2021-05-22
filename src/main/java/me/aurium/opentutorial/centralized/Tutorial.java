package me.aurium.opentutorial.centralized;

import me.aurium.opentutorial.stage.Stage;

import java.util.UUID;

public interface Tutorial {

    /**
     * Get the unique identifier of whatever is sending this
     * @return the identifier of what spawned the tutorial
     */
    UUID getIdentifier();

    /**
     * Gets the identifier of the template that represents this object
     * @return the identifier
     */
    String getTemplateIdentifier();

    Stage getCurrentStage();

    void fireNext();
    void fireCancel();

}
