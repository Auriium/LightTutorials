package gg.qteam.lighttutorials.model;

import java.util.UUID;

public interface SequentialTutorial {

    /**
     * Get the underlying identifier of whatever is sending this
     * @return the identifier of what spawned the tutorial
     */
    UUID getSpawnedIdentifier();

    void fireContinue();

}
