package xyz.auriium.opentutorial.spigot.platform;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.platform.base.Teachable;
import xyz.auriium.opentutorial.core.platform.base.UserRegistry;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class SpigotTeachableRegistry implements UserRegistry<Player> {

    private final JavaPlugin plugin;
    private final Server server;

    public SpigotTeachableRegistry(JavaPlugin plugin, Server server) {
        this.plugin = plugin;
        this.server = server;
    }

    @Override
    public Optional<Teachable> getAudienceByUUID(UUID uuid) {
        Player player = server.getPlayer(uuid);

        return player == null ? Optional.empty() : Optional.of(wrapUser(player));
    }

    @Override
    public Teachable getAudienceByUUIDThrow(UUID uuid) {

        Player player = server.getPlayer(uuid);

        if (player == null) throw new IllegalStateException("Player does not exist on server when it should: " + uuid);

        return wrapUser(player);
    }

    @Override
    public Collection<Teachable> getAllAccessibleAudiences() {
        return server.getOnlinePlayers().stream().map(this::wrapUser).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Optional<Player> getByUUID(UUID uuid) {
        return Optional.ofNullable(server.getPlayer(uuid));
    }

    @Override
    public Collection<Player> getAllAccessible() {
        return (Collection<Player>) server.getOnlinePlayers(); //this is not my fault.
    }

    @Override
    public Teachable wrapUser(Player user) {
        return new SpigotTeachable(plugin,user);
    }
}
