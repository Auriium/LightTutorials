package xyz.auriium.opentutorial.spigot.platform;

import org.bukkit.ChatColor;
import xyz.auriium.opentutorial.core.platform.Colorer;

public class SpigotColorer implements Colorer {
    @Override
    public String color(String string) {
        return ChatColor.translateAlternateColorCodes('&',string);
    }
}
