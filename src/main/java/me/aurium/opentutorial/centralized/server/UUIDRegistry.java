package me.aurium.opentutorial.centralized.server;

import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface UUIDRegistry {

    Optional<Player> getPlayer(UUID uuid);

}
