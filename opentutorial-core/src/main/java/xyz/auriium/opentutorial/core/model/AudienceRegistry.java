package xyz.auriium.opentutorial.core.model;

import xyz.auriium.opentutorial.core.model.Audience;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface AudienceRegistry {

    Optional<Audience> getAudienceByUUID(UUID uuid);
    Collection<Audience> getAllAccessibleAudiences();

}
