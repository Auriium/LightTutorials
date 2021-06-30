package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.opentutorial.api.construct.Stage;

import java.util.UUID;

/**
 * A basic stage consumer that does not implement {@link xyz.auriium.beetle.utility.aspect.UUIDCloseable}'s close methods
 * @param <T> type
 */
public interface BasicStageConsumer<T extends Stage> extends StageConsumer<T> {

    @Override
    default void close() {

    }

    @Override
    default void closeSingle(UUID uuid) {

    }
}
