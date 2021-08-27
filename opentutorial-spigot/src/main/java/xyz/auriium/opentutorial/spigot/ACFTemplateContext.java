package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BukkitCommandExecutionContext;
import co.aikar.commands.InvalidCommandArgument;
import co.aikar.commands.contexts.ContextResolver;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.opentutorial.core.MissingServiceSupplier;
import xyz.auriium.opentutorial.core.PlatformDependentModule;
import xyz.auriium.opentutorial.core.template.Template;

public class ACFTemplateContext implements ContextResolver<Template, BukkitCommandExecutionContext> {

    private final Platform platform;

    public ACFTemplateContext(Platform platform) {
        this.platform = platform;
    }


    @Override
    public Template getContext(BukkitCommandExecutionContext c) throws InvalidCommandArgument {
        String arg = c.getFirstArg();

        PlatformDependentModule module = platform.serviceRegistry()
                .retrieve(PlatformDependentModule.class)
                .orElseThrow(new MissingServiceSupplier("plugin-core"));

        return module.templateController()
                .getByIdentifier(arg)
                .orElseThrow(() -> {
                    module.configController().getMessageConfig().invalidTemplateMessage().send(platform.interRegistry().get(c.getPlayer().getUniqueId()));

                    return new InvalidCommandArgument(false);
                });
    }
}
