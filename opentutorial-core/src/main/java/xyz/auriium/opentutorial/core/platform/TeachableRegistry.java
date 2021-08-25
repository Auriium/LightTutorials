package xyz.auriium.opentutorial.core.platform;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Registry of all teachable constructs on the platform
 */
public interface TeachableRegistry {

    /**
     * Gets a teachable by its uuid
     * @param uuid the uuid
     * @return the teachable if present
     */
    Optional<Teachable> getAudienceByUUID(UUID uuid);

    /**
     * Gets a teachable by its uuid, or throws an exception that the user is not present.
     * @param uuid
     * @return
     */
    Teachable getAudienceByUUIDThrow(UUID uuid);

    /**
     * Gets all accessible teachable constructs
     * @return all available teachables on the platform
     */
    Collection<Teachable> getAllAccessibleAudiences();

}
