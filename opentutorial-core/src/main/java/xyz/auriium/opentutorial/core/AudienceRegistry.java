package xyz.auriium.opentutorial.core;

import xyz.auriium.opentutorial.core.model.Audience;

import java.util.Optional;
import java.util.UUID;

public interface AudienceRegistry {

    Optional<Audience> getAudienceByUUID(UUID uuid);

}
