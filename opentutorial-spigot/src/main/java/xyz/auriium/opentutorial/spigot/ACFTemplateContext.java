package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BukkitCommandExecutionContext;
import co.aikar.commands.InvalidCommandArgument;
import co.aikar.commands.contexts.ContextResolver;
import xyz.auriium.opentutorial.core.platform.impl.PlatformDependentLoader;
import xyz.auriium.opentutorial.core.tutorial.Template;
import xyz.auriium.opentutorial.spigot.platform.SpigotTeachable;

public class ACFTemplateContext implements ContextResolver<Template, BukkitCommandExecutionContext> {

    private final PlatformDependentLoader loader;

    public ACFTemplateContext(PlatformDependentLoader loader) {
        this.loader = loader;
    }

    @Override
    public Template getContext(BukkitCommandExecutionContext c) throws InvalidCommandArgument {
        String arg = c.getFirstArg();

        return loader.getModule().templateController().getByIdentifier(arg).orElseThrow(() -> {
            loader.getModule().configController().getMessageConfig().invalidTemplateMessage().send(new SpigotTeachable(c.getPlayer()),arg);

            return new InvalidCommandArgument(false);
        });
    }
}
