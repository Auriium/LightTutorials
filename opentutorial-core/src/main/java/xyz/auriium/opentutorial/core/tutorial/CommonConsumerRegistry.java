package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.opentutorial.core.event.inner.InnerEventBus;
import xyz.auriium.opentutorial.core.tutorial.stage.AwaitConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CommonConsumerRegistry implements ConsumerRegistry {

    private final Map<Class<? extends Stage>, StageConsumer<? extends Stage>> consumers = new HashMap<>();
    private final Map<String, StageSerializer<?>> serializers = new HashMap<>();

    private final InnerEventBus bus;

    public CommonConsumerRegistry(InnerEventBus bus) {
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

    @Override
    public Optional<StageSerializer<?>> getSerializer(String identifier) {
        return Optional.ofNullable(serializers.get(identifier.toLowerCase()));
    }

    @Override
    public <T, E extends Stage> ConsumerRegistry register(StageConsumer<E> stageConsumer, StageSerializer<E> serializer) {

        consumers.put(stageConsumer.stageClass(),stageConsumer);
        serializers.put(serializer.identifier().toLowerCase(),serializer);

        if (stageConsumer instanceof AwaitConsumer) {
            AwaitConsumer<E,T> consumer = (AwaitConsumer<E, T>) stageConsumer;

            bus.register(consumer.eventClass(),consumer);
        }

        return this;
    }

    @Override
    public void startup() {

    }

    @Override
    public void reload() {
        close();
    }

    @Override
    public void shutdown() {
        close();
    }
}
