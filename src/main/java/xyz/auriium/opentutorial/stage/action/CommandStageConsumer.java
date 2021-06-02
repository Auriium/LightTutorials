package xyz.auriium.opentutorial.stage.action;

import xyz.auriium.opentutorial.PluginCommand;
import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.centralized.config.tutorials.Interpret;
import xyz.auriium.opentutorial.centralized.server.UUIDRegistry;
import xyz.auriium.opentutorial.stage.BasicStageConsumer;

public class CommandStageConsumer implements BasicStageConsumer<CommandStage> {

    private final UUIDRegistry registry;
    private final PluginCommand dispatcher;

    public CommandStageConsumer(UUIDRegistry registry, PluginCommand dispatcher) {
        this.registry = registry;
        this.dispatcher = dispatcher;
    }

    @Override
    public void started(CommandStage options, Tutorial continuable) {

        registry.getPlayer(continuable.getIdentifier()).ifPresent(player -> {
            Interpret.ifStringPresent(options.getRunAsConsole(), string -> {
                dispatcher.runCommand(string.replaceAll("%PLAYER%",player.getName()));
            });

            Interpret.ifStringPresent(options.getRunAsPlayer(), string -> {
                dispatcher.runAs(player,string.replaceAll("%PLAYER%",player.getName()));
            });
        });

        continuable.fireNext();
    }

    @Override
    public Class<CommandStage> stageClass() {
        return CommandStage.class;
    }
}
