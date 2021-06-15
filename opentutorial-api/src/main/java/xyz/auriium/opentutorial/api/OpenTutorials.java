package xyz.auriium.opentutorial.api;

import xyz.auriium.opentutorial.api.event.EventBus;

public interface OpenTutorials {

    EventBus getEventBus();
    TutorialAccessor getAccessor();

}
