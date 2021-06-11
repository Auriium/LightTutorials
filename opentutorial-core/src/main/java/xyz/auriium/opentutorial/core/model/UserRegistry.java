package xyz.auriium.opentutorial.core.model;

import xyz.auriium.opentutorial.core.model.AudienceRegistry;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UserRegistry<T> extends AudienceRegistry {

    Optional<T> getByUUID(UUID uuid);
    Collection<T> getAllAccessible();



}