package xyz.auriium.opentutorial.centralized.config;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.arim.dazzleconf.error.InvalidConfigException;

import java.io.IOException;

public class PrettyExceptionHandler implements ConfigExceptionHandler{

    private final JavaPlugin plugin;
    private final Logger logger = LoggerFactory.getLogger("OpenTutorial PrettyFont");

    public PrettyExceptionHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void handle(InvalidConfigException exception) {
        logger.error("An error occurred reading the config! (Config is invalid)", exception);
        sendToOps("&9OpenTutorial &7> &cAn error occurred while trying to read the configuration! Please check console for the full stacktrace and review your configuration!");
    }

    @Override
    public void handle(IOException exception) {
        logger.error("An error occurred reading the config! (Input/Output)", exception);
        sendToOps("&9OpenTutorial &7> &cAn error occurred while trying to read the configuration! A fatal exception occurred, please make an issue on our GitHub!");
    }

    public void sendToOps(String string) {
        plugin.getServer().getOnlinePlayers().stream().parallel().filter(player -> player.hasPermission("")).forEach(player -> {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',string));
        });
    }
}
