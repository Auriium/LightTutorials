package xyz.auriium.opentutorial.core.platform.base;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * AudienceRegistry that also specified a type
 * @param <T> typed user
 */
public interface UserRegistry<T> extends TeachableRegistry {

    /**
     * Gets a user by uuid
     * @param uuid the uuid
     * @return the typed user
     */
    Optional<T> getByUUID(UUID uuid);

    /**
     * Gets all typed users from the platform
     * @return all typed users
     */
    Collection<T> getAllAccessible();



}
