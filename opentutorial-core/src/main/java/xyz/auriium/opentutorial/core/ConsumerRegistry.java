package xyz.auriium.opentutorial.core;

import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.consumer.StageConfigParser;
import xyz.auriium.opentutorial.core.consumer.StageConsumer;

import java.util.Collection;
import java.util.Optional;

public interface ConsumerRegistry {

    Collection<StageConsumer<?,?>> getAll();
    <T extends Stage, E> Optional<StageConsumer<T,E>> get(Class<T> clazz);
    Optional<StageConfigParser> getInsertion(String insertionName);

    ConsumerRegistry register(StageConsumer<?,?> consumer);

}
