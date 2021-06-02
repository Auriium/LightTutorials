package xyz.auriium.opentutorial.core;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UserRegistry<T> {

    Optional<T> getByUUID(UUID uuid);
    Collection<T> getAllAccessible();

}
