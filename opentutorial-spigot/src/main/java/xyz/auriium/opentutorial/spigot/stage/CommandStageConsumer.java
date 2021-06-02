package xyz.auriium.opentutorial.spigot.stage;

import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.UserRegistry;
import xyz.auriium.opentutorial.core.config.types.tutorials.Interpret;
import xyz.auriium.opentutorial.core.model.Dispatcher;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.BasicStageConsumer;

public class CommandStageConsumer implements BasicStageConsumer<CommandStage> {

    private final UserRegistry<Player> registry;
    private final Dispatcher<Player> dispatcher;

    public CommandStageConsumer(UserRegistry<Player> registry, Dispatcher<Player> dispatcher) {
        this.registry = registry;
        this.dispatcher = dispatcher;
    }

    @Override
    public void started(CommandStage options, Tutorial continuable) {

        registry.getByUUID(continuable.getIdentifier()).ifPresent(player -> {
            Interpret.ifStringPresent(options.getRunAsConsole(), string -> {
                dispatcher.dispatchCommand(string.replaceAll("%PLAYER%",player.getName()));
            });

            Interpret.ifStringPresent(options.getRunAsPlayer(), string -> {
                dispatcher.dispatchAs(player,string.replaceAll("%PLAYER%",player.getName()));
            });
        });

        continuable.fireNext();
    }

    @Override
    public Class<CommandStage> stageClass() {
        return CommandStage.class;
    }
}
