package me.aurium.opentutorial.centralized.registry;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.util.MultiMap;
import me.aurium.opentutorial.util.OrderedMultiMap;

public class CommonEventBus implements EventBus {

    private final MultiMap<Class<? extends Event>, EventConsumer<? extends Event>> map = new OrderedMultiMap<>();

    @Override
    public <E extends Event> void fire(E event, Tutorial tutorial) {
        map.forEach(event.getClass(), consumer -> {
            ((EventConsumer<E>) consumer).consume(event, tutorial);
        });

    }

    @Override
    public <E extends Event> void register(Class<E> clazz, EventConsumer<E> consumer) {
        map.insert(clazz,consumer);
    }
}
