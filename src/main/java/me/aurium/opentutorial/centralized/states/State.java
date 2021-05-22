package me.aurium.opentutorial.centralized.states;

import me.aurium.opentutorial.aspect.UUIDCloseable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

/**
 * Represents a state bound to a user's key.
 */
public interface State {

    UUID userID();

    void activate();
    void deactivate();

}
