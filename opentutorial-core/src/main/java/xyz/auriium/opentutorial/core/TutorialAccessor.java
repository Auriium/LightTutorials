package xyz.auriium.opentutorial.core;

import xyz.auriium.opentutorial.core.tutorial.Template;

import java.util.UUID;

/**
 * Represents a publicly outward facing accessor for access to begin and end tutorials.
 */
public interface TutorialAccessor {

    void startTutorial(Template name, UUID uuid);
    void stopTutorial(UUID uuid);

}
