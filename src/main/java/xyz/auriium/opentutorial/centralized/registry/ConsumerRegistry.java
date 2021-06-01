package xyz.auriium.opentutorial.centralized.registry;


import me.aurium.beetle.defaults.utility.aspect.UUIDCloseable;
import xyz.auriium.opentutorial.centralized.NoConsumerException;
import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.stage.Stage;
import xyz.auriium.opentutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.stage.StageSerializer;

import java.util.Optional;

/**
 * Registry for consumer packages that allows access to both the consumer of an installed package as well as the linked serializer
 */
public interface ConsumerRegistry extends UUIDCloseable {

    /**
     * Allows a stage to be processed by a consumer
     * @param stage the stage
     * @throws NoConsumerException if there is no consumer ready to handle the stage submitted
     */
    <T extends Stage> void consumeStage(T stage, Tutorial tutorial);

    Optional<StageSerializer<?>> getSerializer(String identifier);

    /**
     * Installs a consumer package
     * @param consumer the consumer package
     */
    <T extends Event, E extends Stage> ConsumerRegistry register(StageConsumer<E> stage, StageSerializer<E> consumer);

}
