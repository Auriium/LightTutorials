package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BukkitCommandExecutionContext;
import co.aikar.commands.InvalidCommandArgument;
import co.aikar.commands.contexts.ContextResolver;
import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.platform.UserRegistry;
import xyz.auriium.opentutorial.core.platform.impl.PlatformDependentLoader;
import xyz.auriium.opentutorial.core.tutorial.Template;

public class ACFTemplateContext implements ContextResolver<Template, BukkitCommandExecutionContext> {

    private final UserRegistry<Player> registry;
    private final PlatformDependentLoader<Player> loader;

    public ACFTemplateContext(UserRegistry<Player> registry, PlatformDependentLoader<Player> loader) {
        this.registry = registry;
        this.loader = loader;
    }


    @Override
    public Template getContext(BukkitCommandExecutionContext c) throws InvalidCommandArgument {
        String arg = c.getFirstArg();

        return loader.getModule().templateController().getByIdentifier(arg).orElseThrow(() -> {
            loader.getModule().configController().getMessageConfig().invalidTemplateMessage().send(registry.wrapUser(c.getPlayer()),arg);

            return new InvalidCommandArgument(false);
        });
    }
}
