package xyz.auriium.opentutorial.core.event;

import xyz.auriium.opentutorial.core.ConsumerRegistry;
import xyz.auriium.opentutorial.core.consumer.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.ConsumerFailureConduit;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;

import java.util.UUID;

public class ReduxEventBus implements EventBus {

    private final ConsumerRegistry registry;
    private final TutorialController tutorialController;
    private final ConsumerFailureConduit consumerFailureConduit;

    public ReduxEventBus(ConsumerRegistry registry, TutorialController tutorialController, ConsumerFailureConduit consumerFailureConduit) {
        this.registry = registry;
        this.tutorialController = tutorialController;
        this.consumerFailureConduit = consumerFailureConduit;
    }

    @Override
    public <E extends Event> void fire(E event, Tutorial tutorial) {
        for (StageConsumer<?,?> uncast : registry.getAll()) {
            if (uncast.eventClass().isInstance(event)) {
                StageConsumer<?,E> consumer = (StageConsumer<?, E>) uncast;

                consumerFailureConduit.handle(tutorial, () -> consumer.eventCalled(event, tutorial));
            }
        }
    }

    @Override
    public <E extends AssociatedEvent> void fire(E event) {
        tutorialController.getByUUID(event.getAssociated()).ifPresent(tutorial -> fire(event, tutorial));
    }

    @Override
    public <E extends Event> void fire(E event, UUID tutorial) {
        tutorialController.getByUUID(tutorial).ifPresent(tuto -> fire(event, tuto));
    }
}
