package xyz.auriium.opentutorial.spigot;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.UserRegistry;
import xyz.auriium.opentutorial.core.model.Audience;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class SpigotUserRegistry implements UserRegistry<Player> {

    private final JavaPlugin plugin;

    public SpigotUserRegistry(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Optional<Player> getByUUID(UUID uuid) {
        return Optional.ofNullable(plugin.getServer().getPlayer(uuid));
    }

    @SuppressWarnings("unchecked") //why does this have to return ? extends Player? there are no player etensions...??? bukkit is retarded
    @Override
    public Collection<Player> getAllAccessible() {
        return (Collection<Player>) plugin.getServer().getOnlinePlayers();
    }

    @Override
    public Optional<Audience> getAudienceByUUID(UUID uuid) {
        Player player = plugin.getServer().getPlayer(uuid);

        if (player != null) {
            return Optional.of(SpigotAudience.wrap(player));
        } else {
            return Optional.empty();
        }
    }
}
