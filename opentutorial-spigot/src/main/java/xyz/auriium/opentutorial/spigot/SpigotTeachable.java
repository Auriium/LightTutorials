package xyz.auriium.opentutorial.spigot;

import org.bukkit.command.CommandSender;
import xyz.auriium.opentutorial.core.platform.base.Teachable;

public class SpigotTeachable implements Teachable {

    private final CommandSender delegate;

    public SpigotTeachable(CommandSender delegate) {
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

    public static SpigotTeachable wrap(CommandSender sender) {
        return new SpigotTeachable(sender);
    }
}
