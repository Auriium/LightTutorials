package me.aurium.opentutorial.centralized;

import me.aurium.opentutorial.aspect.UUIDCloseable;
import me.aurium.opentutorial.centralized.registry.ConsumerRegistry;
import me.aurium.opentutorial.centralized.template.Template;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Manages all tutorials. Center of the plugin.
 */
public interface TutorialController extends UUIDCloseable {

    /**
     * Cancels by UUID
     * @param uuid the uuid
     * @return cancelled tutorial
     */
    Optional<Tutorial> cancelByUUID(UUID uuid);

    /**
     * Get the registry bound to this controller
     * @return te reg
     */
    ConsumerRegistry getRegistry();

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

}
