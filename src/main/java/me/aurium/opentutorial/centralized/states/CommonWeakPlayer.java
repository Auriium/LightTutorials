package me.aurium.opentutorial.centralized.states;

import me.aurium.opentutorial.centralized.server.UUIDRegistry;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * icky and gross but what can we do about it
 */
public class CommonWeakPlayer implements WeakPlayer {

    private final UUIDRegistry server;
    private final UUID uuid;

    public CommonWeakPlayer(UUIDRegistry server, UUID uuid) {
        this.server = server;
        this.uuid = uuid;
    }

    @Override
    public UUID getID() {
        return uuid;
    }

    @Override
    public Optional<Player> getPlayer() {
        return server.getPlayer(uuid);
    }

    @Override
    public Player getPlayerAssert() {
        return server.getPlayer(uuid).orElseThrow(() -> new IllegalStateException("Tried to get player but none existed for UUID!"));
    }

    @Override
    public void doPlayer(Consumer<Player> consumer) {
        getPlayer().ifPresent(consumer);
    }
}
