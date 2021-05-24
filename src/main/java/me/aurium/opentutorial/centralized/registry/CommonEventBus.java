package me.aurium.opentutorial.centralized.registry;

import me.aurium.beetle.defaults.utility.map.multi.MultiMap;
import me.aurium.beetle.defaults.utility.map.multi.OrderedMultiMap;
import me.aurium.opentutorial.centralized.Tutorial;

public class CommonEventBus implements EventBus {

    private final MultiMap<Class<? extends Event>, EventConsumer<? extends Event>> map = new OrderedMultiMap<>();

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
