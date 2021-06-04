package xyz.auriium.opentutorial.spigot;

import org.bukkit.command.CommandSender;
import xyz.auriium.opentutorial.core.model.Audience;

public class SpigotAudience implements Audience {

    private final CommandSender delegate;

    public SpigotAudience(CommandSender delegate) {
        this.delegate = delegate;
    }

    @Override
    public void sendMessage(String string) {
        delegate.sendMessage(string);
    }

    public static SpigotAudience wrap(CommandSender sender) {
        return new SpigotAudience(sender);
    }
}
