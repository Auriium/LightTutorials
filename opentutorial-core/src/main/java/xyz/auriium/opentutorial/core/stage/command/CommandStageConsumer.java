package xyz.auriium.opentutorial.core.stage.command;

import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.BasicStageConsumer;

/**
 * Consumer system that handles {@link CommandStage}
 */
public class CommandStageConsumer implements BasicStageConsumer<CommandStage> {

    private final TeachableRegistry teachableRegistry;

    public CommandStageConsumer(TeachableRegistry teachableRegistry) {
        this.teachableRegistry = teachableRegistry;
    }


    @Override
    public void started(CommandStage options, Tutorial continuable) {

        teachableRegistry.getAudienceByUUID(continuable.getIdentifier()).ifPresent(player -> {
            Interpret.ifStringPresent(options.getRunAsConsole(), string -> {
                player.runConsole(string.replaceAll("%PLAYER%",player.getName()));
            });

            Interpret.ifStringPresent(options.getRunAsPlayer(), string -> {
                player.runAs(string.replaceAll("%PLAYER%",player.getName()));
            });
        });

        continuable.fireNext();
    }

    @Override
    public Class<CommandStage> stageClass() {
        return CommandStage.class;
    }
}
