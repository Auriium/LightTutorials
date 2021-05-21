package gg.qteam.lighttutorials.stage.await;

import space.arim.omnibus.events.Event;

import java.util.UUID;

public interface IdentifiableEvent extends Event {

    /**
     * Get the ID of what spawned this
     * @return ID
     */
    UUID getSpawnedIdentifier();

}
