package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BukkitCommandCompletionContext;
import co.aikar.commands.CommandCompletions;
import co.aikar.commands.InvalidCommandArgument;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.opentutorial.core.MissingServiceSupplier;
import xyz.auriium.opentutorial.core.PlatformDependentModule;

import java.util.Collection;

public class ACFConsumer implements CommandCompletions.CommandCompletionHandler<BukkitCommandCompletionContext> {

    private final Platform platform;

    public ACFConsumer(Platform platform) {
        this.platform = platform;
    }

    @Override
    public Collection<String> getCompletions(BukkitCommandCompletionContext context) throws InvalidCommandArgument {
        return platform.serviceRegistry()
                .retrieve(PlatformDependentModule.class)
                .orElseThrow(new MissingServiceSupplier("plugin-core"))
                .templateController()
                .getTemplateNames();
    }
}
