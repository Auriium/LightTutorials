package me.aurium.opentutorial.centralized;

import me.aurium.opentutorial.stage.Stage;

import java.util.UUID;

public interface Tutorial {

    /**
     * Get the underlying identifier of whatever is sending this
     * @return the identifier of what spawned the tutorial
     */
    UUID getIdentifier();

    Stage getCurrentStage();

    void fireNext();
    void fireCancel();

}
