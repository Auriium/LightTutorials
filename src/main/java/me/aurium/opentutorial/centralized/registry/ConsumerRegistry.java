package me.aurium.opentutorial.centralized.registry;

import me.aurium.opentutorial.aspect.UUIDCloseable;
import me.aurium.opentutorial.centralized.NoConsumerException;
import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.ConsumerPackage;
import me.aurium.opentutorial.stage.ConsumerSerializer;
import me.aurium.opentutorial.stage.Stage;
import me.aurium.opentutorial.stage.StageConsumer;

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

    Optional<ConsumerSerializer<?>> getSerializer(String identifier);

    /**
     * Installs a consumer package
     * @param consumer the consumer package
     */
    <T extends Event, E extends Stage> void register(StageConsumer<E> stage, ConsumerSerializer<E> consumer);

}
