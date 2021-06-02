package xyz.auriium.opentutorial.centralized.registry;

import xyz.auriium.beetle.utility.map.multi.DelegatingMultiMap;
import xyz.auriium.beetle.utility.map.multi.MultiMap;
import xyz.auriium.opentutorial.centralized.Tutorial;

public class CommonEventBus implements EventBus {

    private final MultiMap<Class<? extends Event>, EventConsumer<? extends Event>> map = new DelegatingMultiMap<>();

    @SuppressWarnings("unchecked") //insertion is ""checked"": see register();
    @Override
    public <E extends Event> void fire(E event, Tutorial tutorial) {
        map.forEach(event.getClass(), consumer -> ((EventConsumer<E>) consumer).consume(event, tutorial));

    }

    @Override
    public <E extends Event> void register(Class<E> clazz, EventConsumer<E> consumer) {
        map.insert(clazz,consumer);
    }
}
