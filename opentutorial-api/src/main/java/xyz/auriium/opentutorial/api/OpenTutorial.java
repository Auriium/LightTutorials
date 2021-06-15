package xyz.auriium.opentutorial.api;

import xyz.auriium.opentutorial.api.event.EventBus;

public interface OpenTutorial {

    EventBus getEventBus();
    TutorialAccessor getAccessor();

}
