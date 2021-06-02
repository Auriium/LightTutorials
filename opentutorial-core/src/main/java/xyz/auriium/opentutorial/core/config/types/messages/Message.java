package xyz.auriium.opentutorial.core.config.types.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Message {

    private final String translatable;

    public Message(String translatable) {
        this.translatable = translatable;
    }

    public void send(CommandSender sender, Object... strings) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',String.format(translatable,strings)));
    }
    public void send(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',translatable));
    }
}
