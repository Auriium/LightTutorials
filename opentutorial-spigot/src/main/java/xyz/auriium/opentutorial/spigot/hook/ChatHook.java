package xyz.auriium.opentutorial.spigot.hook;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.auriium.opentutorial.core.stage.chat.Suppressor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ChatHook implements Listener, Suppressor {

    private final Set<UUID> suppressed = new HashSet<>();

    @EventHandler
    public void playerChatEvent(AsyncPlayerChatEvent event) {
        if (suppressed.contains(event.getPlayer().getUniqueId())) {
            Set<Player> players = new HashSet<>();

            for (Player player : event.getRecipients()) {
                if (player.hasPermission("opentutorial.bypass.chat")) {
                    players.add(player);
                }
            }

            event.getRecipients().clear();
            event.getRecipients().addAll(players);
        }
    }

    @Override
    public void setSuppressed(UUID uuid, Boolean locked) {
        if (locked) {
            suppressed.add(uuid);
        } else {
            suppressed.remove(uuid);
        }
    }

    @Override
    public void removeOne(UUID uuid) {
        suppressed.remove(uuid);
    }

    @Override
    public void removeAll() {
        suppressed.clear();
    }
}
