package xyz.auriium.opentutorial.core.event;

import xyz.auriium.beetle.utility.map.multi.MultiMap;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;

import java.util.UUID;

public class CommonInnerEventBus implements InnerEventBus {

    private final MultiMap<Class<?>, InnerEventConsumer<?>> map;
    private final TutorialController tutorialController;

    public CommonInnerEventBus(MultiMap<Class<?>, InnerEventConsumer<?>> map, TutorialController tutorialController) {
        this.map = map;
        this.tutorialController = tutorialController;
    }

    @SuppressWarnings("unchecked") //insertion is ""checked"": see register();
    @Override
    public <E extends Event> void fire(E event, Tutorial tutorial) {
        map.forEach(event.getClass(), consumer -> ((InnerEventConsumer<E>) consumer).consume(event, tutorial));

    }

    @Override
    public <E extends AssociatedEvent> void fire(E event) {
        tutorialController.getByUUID(event.getAssociated()).ifPresent(tutorial -> {
            fire(event,tutorial);
        });
    }

    @Override
    public <E extends Event> void fire(E event, UUID tutorial) {
        tutorialController.getByUUID(tutorial).ifPresent(t -> {
            fire(event,t);
        });
    }
}
