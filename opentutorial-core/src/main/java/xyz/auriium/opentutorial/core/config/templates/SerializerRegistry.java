package xyz.auriium.opentutorial.core.config.templates;

import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

import java.util.Optional;

public interface SerializerRegistry {

    Optional<StageSerializer<?>> getSerializer(String identifier);

    /**
     * Installs a serializer package
     * @param serializer the serializer package
     */
    <E extends Stage> SerializerRegistry register(StageSerializer<E> serializer);

}
