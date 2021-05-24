package me.aurium.opentutorial.centralized.states;

import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Weak reference to a player
 */
public interface WeakPlayer {

    /**
     * Gets the player's id
     * @return the id
     */
    UUID getID();

    /**
     * Get the player if present
     * @return the player
     */
    Optional<Player> getPlayer();

    /**
     * Gets the player
     * @return the player, or null
     */
    Player getPlayerAssert();

    /**
     * Does an action to the player if present
     * @param consumer the action
     */
    void doPlayer(Consumer<Player> consumer);

}
