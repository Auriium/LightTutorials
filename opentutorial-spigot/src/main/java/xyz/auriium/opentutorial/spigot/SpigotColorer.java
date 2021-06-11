package xyz.auriium.opentutorial.spigot;

import org.bukkit.ChatColor;
import xyz.auriium.opentutorial.core.model.Colorer;

public class SpigotColorer implements Colorer {
    @Override
    public String color(String string) {
        return ChatColor.translateAlternateColorCodes('&',string);
    }
}
