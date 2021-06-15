package xyz.auriium.opentutorial.core.tutorial.impl;

import xyz.auriium.opentutorial.core.event.Event;
import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.stage.AwaitConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CommonConsumerCentralizer implements ConsumerCentralizer {

    private final Map<Class<? extends Stage>, StageConsumer<? extends Stage>> consumers;
    private final InnerEventBus bus;

    public CommonConsumerCentralizer(Map<Class<? extends Stage>, StageConsumer<? extends Stage>> consumers, InnerEventBus bus) {
        this.consumers = consumers;
        this.bus = bus;
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

        if (stageConsumer instanceof AwaitConsumer) {
            AwaitConsumer<E,T> consumer = (AwaitConsumer<E, T>) stageConsumer;

            bus.register(consumer.eventClass(),consumer);
        }

        return this;
    }

}
