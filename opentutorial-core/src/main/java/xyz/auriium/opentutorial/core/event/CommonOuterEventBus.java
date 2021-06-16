package xyz.auriium.opentutorial.core.event;

import xyz.auriium.opentutorial.core.tutorial.TutorialController;

import java.util.UUID;

public class CommonOuterEventBus implements OuterEventBus{

    private final TutorialController tutorialController;
    private final InnerEventBus innerEventBus;

    public CommonOuterEventBus(TutorialController tutorialController, InnerEventBus innerEventBus) {
        this.tutorialController = tutorialController;
        this.innerEventBus = innerEventBus;
    }

    @Override
    public <E extends AssociatedEvent> void fire(E event) {
        tutorialController.getByUUID(event.getAssociated()).ifPresent(tutorial -> {
            innerEventBus.fire(event,tutorial);
        });
    }

    @Override
    public <E extends Event> void fire(E event, UUID tutorial) {
        tutorialController.getByUUID(tutorial).ifPresent(t -> {
            innerEventBus.fire(event,t);
        });
    }
}
