package xyz.auriium.opentutorial.core.platform.base;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Registry of all teachable constructs on the platform
 */
public interface TeachableRegistry {

    /**
     * Gets a teachable by it's uuid
     * @param uuid the uuid
     * @return the teachable if present
     */
    Optional<Teachable> getAudienceByUUID(UUID uuid);

    /**
     * Gets all accessible teachable constructs
     * @return all available teachables on the platform
     */
    Collection<Teachable> getAllAccessibleAudiences();

}
