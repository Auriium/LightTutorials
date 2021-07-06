package xyz.auriium.opentutorial.core.tutorial.impl;

import xyz.auriium.opentutorial.api.construct.Stage;
import xyz.auriium.opentutorial.core.event.Event;
import xyz.auriium.opentutorial.core.event.hook.HookRegistry;
import xyz.auriium.opentutorial.core.event.hook.PrecompletedHookInsertion;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.stage.AwaitConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CommonConsumerCentralizer implements ConsumerCentralizer {

    private final Map<Class<? extends Stage>, StageConsumer<? extends Stage>> consumers;

    public CommonConsumerCentralizer(Map<Class<? extends Stage>, StageConsumer<? extends Stage>> consumers) {
        this.consumers = consumers;
    }

    @Override
    public void closeSingle(UUID uuid) {
        for (StageConsumer<? extends Stage> consumer : consumers.values()) {
            consumer.closeSingle(uuid);
        }
    }

    @Override
    public void close() {
        for (StageConsumer<? extends Stage> consumer : consumers.values()) {
            consumer.close();
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Stage> Optional<StageConsumer<T>> getConsumer(T stage) {
        StageConsumer<T> consumer = (StageConsumer<T>) consumers.get(stage.getClass());

        return Optional.ofNullable(consumer);
    }

    public <T extends Event,E extends Stage> ConsumerCentralizer register(StageConsumer<E> stageConsumer) {

        consumers.put(stageConsumer.stageClass(),stageConsumer);



        return this;
    }

}
