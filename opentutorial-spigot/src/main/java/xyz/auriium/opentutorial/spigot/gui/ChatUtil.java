package xyz.auriium.opentutorial.spigot.gui;

import org.bukkit.ChatColor;

public class ChatUtil {

    static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&',string);
    }

}
