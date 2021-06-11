package xyz.auriium.opentutorial.spigot.stage;

import xyz.auriium.opentutorial.core.model.AudienceRegistry;
import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.BasicStageConsumer;

public class CommandStageConsumer implements BasicStageConsumer<CommandStage> {

    private final AudienceRegistry audienceRegistry;

    public CommandStageConsumer(AudienceRegistry audienceRegistry) {
        this.audienceRegistry = audienceRegistry;
    }


    @Override
    public void started(CommandStage options, Tutorial continuable) {

        audienceRegistry.getAudienceByUUID(continuable.getIdentifier()).ifPresent(player -> {
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
