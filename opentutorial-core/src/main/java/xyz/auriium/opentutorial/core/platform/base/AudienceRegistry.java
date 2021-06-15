package xyz.auriium.opentutorial.core.platform.base;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface AudienceRegistry {

    Optional<Audience> getAudienceByUUID(UUID uuid);
    Collection<Audience> getAllAccessibleAudiences();

}
