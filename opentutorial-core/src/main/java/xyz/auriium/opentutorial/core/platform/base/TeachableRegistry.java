package xyz.auriium.opentutorial.core.platform.base;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface TeachableRegistry {

    Optional<Teachable> getAudienceByUUID(UUID uuid);
    Collection<Teachable> getAllAccessibleAudiences();

}
