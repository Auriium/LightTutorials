package xyz.auriium.opentutorial.core.consumer;

import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.Optional;

/**
 * Initialized consumer centralizer for handling the passing of stages to different consumers
 */
public interface ConsumerCentralizer {

    /**
     * Gets a consumer based on a stage
     * @param stage the stage to look for
     * @param <T> stage type
     * @return the corresponding stage consumer if present
     */
    <T extends Stage, E> Optional<StageConsumer<T,E>> getConsumer(T stage);

    /**
     * Consumes a stage
     * @param stage
     * @param <T>
     * @param <E>
     */
    <T extends Stage, E> void consumeStage(T stage, Tutorial tutorial);


}
