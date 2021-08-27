package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.littlethings.archetypes.closeables.UUIDCloseable;
import xyz.auriium.opentutorial.core.template.Template;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents a tutorial controller that can cancel and start tutorials.
 */
public interface TutorialController extends UUIDCloseable {

    /**
     * Cancels by UUID if present or does nothing if not
     *
     * @param uuid the uuid
     * @return cancelled tutorial
     */
    Optional<Tutorial> cancelByUUID(UUID uuid);

    /**
     * Gets a tutorial by UUID
     *
     * @param uuid the id
     * @return cancelled tutorial
     */
    Optional<Tutorial> getByUUID(UUID uuid);

    /**
     * Create a new tutorial and register it to the controller
     *
     * NOTE THAT THIS DOES NOT START THE TUTORIAL.
     *
     * Use the start method (which is literally just a less retarded way of spelling the fireContinue method.)
     *
     * @param template the template you want to use
     * @return the generated tutorial
     */
    Tutorial createNew(Template template, UUID owner);

    /**
     * Creates a tutorial that consists of a single stage from a template
     *
     * Must be started manually
     *
     * @param template template used
     * @param owner owner
     * @param stage stage to select
     * @return etc
     */
    Tutorial createStage(Template template, UUID owner, int stage);

}
