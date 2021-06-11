package xyz.auriium.opentutorial.spigot;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.UserRegistry;
import xyz.auriium.opentutorial.core.config.PrettyExceptionHandler;

public class SpigotExceptionHandler extends PrettyExceptionHandler<Player> {

    public SpigotExceptionHandler(UserRegistry<Player> registry) {
        super(registry);
    }

    @Override
    protected void relayErrorMessage(Player player, String string) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',string));
    }
}
