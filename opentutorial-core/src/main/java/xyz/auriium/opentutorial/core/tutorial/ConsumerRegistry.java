package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.beetle.utility.aspect.KeyCloseable;
import xyz.auriium.opentutorial.core.model.Cycleable;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

import java.util.Optional;
import java.util.UUID;

public interface ConsumerRegistry extends Cycleable, KeyCloseable<UUID> {

    <T extends Stage> Optional<StageConsumer<T>> getConsumer(T stage);
    /**
     * Installs a consumer
     * @param stage the consumer
     */
    <T,E extends Stage> ConsumerRegistry register(StageConsumer<E> stage);

}
