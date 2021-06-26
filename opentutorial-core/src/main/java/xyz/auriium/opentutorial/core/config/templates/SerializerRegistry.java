package xyz.auriium.opentutorial.core.config.templates;

import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;

import java.util.Optional;

public interface SerializerRegistry {

    Optional<StageInsertion> getSerializer(String identifier);

    /**
     * Installs a serializer package
     * @param serializer the serializer package
     */
    SerializerRegistry register(StageInsertion serializer);

}
