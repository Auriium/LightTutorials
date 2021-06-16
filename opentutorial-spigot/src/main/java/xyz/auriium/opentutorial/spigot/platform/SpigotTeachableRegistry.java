package xyz.auriium.opentutorial.spigot.platform;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.platform.base.Teachable;
import xyz.auriium.opentutorial.core.platform.base.UserRegistry;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class SpigotTeachableRegistry implements UserRegistry<Player> {

    private final Server server;

    public SpigotTeachableRegistry(Server server) {
        this.server = server;
    }

    @Override
    public Optional<Teachable> getAudienceByUUID(UUID uuid) {
        Player player = server.getPlayer(uuid);

        return player == null ? Optional.empty() : Optional.of(new SpigotTeachable(player));
    }

    @Override
    public Collection<Teachable> getAllAccessibleAudiences() {
        return server.getOnlinePlayers().stream().map(SpigotTeachable::new).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Optional<Player> getByUUID(UUID uuid) {
        return Optional.ofNullable(server.getPlayer(uuid));
    }

    @Override
    public Collection<Player> getAllAccessible() {
        return (Collection<Player>) server.getOnlinePlayers();
    }
}
