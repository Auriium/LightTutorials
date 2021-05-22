package me.aurium.opentutorial.stage;

/**
 * Wacky
 * @param <T> wacky
 */
public class ConsumerPackage<T extends Stage> {

    private final ConsumerSerializer<T> serializer;
    private final StageConsumer<T> consumer;

    public ConsumerPackage(ConsumerSerializer<T> serializer, StageConsumer<T> consumer) {
        this.serializer = serializer;
        this.consumer = consumer;
    }

    public ConsumerSerializer<T> getSerializer() {
        return serializer;
    }

    public StageConsumer<T> getConsumer() {
        return consumer;
    }
}
