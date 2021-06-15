package xyz.auriium.opentutorial.spigot;

import org.bukkit.command.CommandSender;
import xyz.auriium.opentutorial.core.platform.base.Audience;

public class SpigotAudience implements Audience {

    private final CommandSender delegate;

    public SpigotAudience(CommandSender delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public void sendMessage(String string) {
        delegate.sendMessage(string);
    }

    @Override
    public void runAs(String command) {
        delegate.getServer().dispatchCommand(delegate,command);
    }

    @Override
    public void runConsole(String command) {
        delegate.getServer().dispatchCommand(delegate.getServer().getConsoleSender(),command);
    }

    public static SpigotAudience wrap(CommandSender sender) {
        return new SpigotAudience(sender);
    }
}
