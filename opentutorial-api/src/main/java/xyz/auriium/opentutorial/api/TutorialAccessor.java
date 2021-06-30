package xyz.auriium.opentutorial.api;

import xyz.auriium.opentutorial.api.construct.Template;

import java.util.UUID;

/**
 * Represents a publicly outward facing accessor for access to begin and end tutorials.
 */
public interface TutorialAccessor {

    void startTutorial(Template name, UUID uuid);
    void stopTutorial(UUID uuid);

}
