package xyz.auriium.opentutorial.centralized.server;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;
import java.util.UUID;

public class WrappedUUIDRegistry implements UUIDRegistry{

    private final JavaPlugin plugin;

    public WrappedUUIDRegistry(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Optional<Player> getPlayer(UUID uuid) {
        return Optional.ofNullable(plugin.getServer().getPlayer(uuid));
    }
}
