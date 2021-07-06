package xyz.auriium.opentutorial.core.stage.command;

import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.api.construct.Tutorial;
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
            options.getRunAsConsole().ifPresent(string -> player.runConsole(string.replaceAll("%PLAYER%",player.getName())));
            options.getRunAsPlayer().ifPresent(string -> player.runAs(string.replaceAll("%PLAYER%",player.getName())));
        });

        continuable.fireNext();
    }

    @Override
    public Class<CommandStage> stageClass() {
        return CommandStage.class;
    }
}
