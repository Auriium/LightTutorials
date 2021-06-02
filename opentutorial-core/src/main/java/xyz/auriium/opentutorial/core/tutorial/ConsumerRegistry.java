package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.beetle.utility.aspect.KeyCloseable;
import xyz.auriium.opentutorial.core.model.Cycleable;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

import java.util.Optional;
import java.util.UUID;

public interface ConsumerRegistry extends Cycleable, KeyCloseable<UUID> {

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
    <T, E extends Stage> ConsumerRegistry register(StageConsumer<E> stage, StageSerializer<E> consumer);

}
