package xyz.auriium.opentutorial.core.event;

import xyz.auriium.beetle.utility.map.multi.DelegatingMultiMap;
import xyz.auriium.beetle.utility.map.multi.MultiMap;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public class CommonEventBus implements EventBus {

    private final MultiMap<Class<?>, EventConsumer<?>> map = new DelegatingMultiMap<>();

    @SuppressWarnings("unchecked") //insertion is ""checked"": see register();
    @Override
    public <E> void fire(E event, Tutorial tutorial) {
        map.forEach(event.getClass(), consumer -> ((EventConsumer<E>) consumer).consume(event, tutorial));

    }

    @Override
    public <E> void register(Class<E> clazz, EventConsumer<E> consumer) {
        map.insert(clazz,consumer);
    }
}
