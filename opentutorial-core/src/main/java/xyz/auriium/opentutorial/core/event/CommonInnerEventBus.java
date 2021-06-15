package xyz.auriium.opentutorial.core.event;

import xyz.auriium.beetle.utility.map.multi.DelegatingMultiMap;
import xyz.auriium.beetle.utility.map.multi.MultiMap;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;

import java.util.UUID;

public class CommonInnerEventBus implements InnerEventBus {

    private final MultiMap<Class<?>, InnerEventConsumer<?>> map = new DelegatingMultiMap<>();

    @SuppressWarnings("unchecked") //insertion is ""checked"": see register();
    @Override
    public <E extends Event> void fire(E event, Tutorial tutorial) {
        map.forEach(event.getClass(), consumer -> ((InnerEventConsumer<E>) consumer).consume(event, tutorial));

    }

    @Override
    public <E extends Event> void register(Class<E> clazz, InnerEventConsumer<E> consumer) {
        map.insert(clazz,consumer);
    }
}
