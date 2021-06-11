package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.beetle.utility.aspect.KeyCloseable;
import xyz.auriium.opentutorial.core.model.Cycleable;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.template.Template;

import java.util.Optional;
import java.util.UUID;

public interface TutorialController extends Cycleable, KeyCloseable<UUID> {

    <T extends Stage> void consumeStage(T stage, Tutorial tutorial);

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
