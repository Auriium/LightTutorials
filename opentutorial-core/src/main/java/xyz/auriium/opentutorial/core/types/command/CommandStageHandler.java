package xyz.auriium.opentutorial.core.types.command;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.openmineplatform.api.interfaceable.user.User;
import xyz.auriium.openmineplatform.api.interfaceable.user.UserTelescope;
import xyz.auriium.opentutorial.core.StageExceptionMapper;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.BasicConsumer;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.Map;

public class CommandStageHandler implements BasicConsumer<CommandStage> {
    @Override
    public String identifier() {
        return "command";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        String runAsConsole = Interpret.getNullable(Identifiers.COMMAND_CONSOLE,map,FlexibleType::getString);
        String runAsPlayer = Interpret.getNullable(Identifiers.COMMAND_PLAYER,map,FlexibleType::getString);

        return new CommandStage(runAsConsole,runAsPlayer);
    }

    @Override
    public void stageStarted(CommandStage options, Tutorial tutorial) {
        User user = tutorial.getPlatform()
                .interRegistry()
                .getTelescoping(tutorial.getIdentifier(), StageExceptionMapper.USER);

        options.getRunAsConsole().ifPresent(user::runCommandAsPlatform);
        options.getRunAsPlayer().ifPresent(user::runCommandAsUser);

        tutorial.fireNext();
    }

    @Override
    public Class<CommandStage> stageClass() {
        return CommandStage.class;
    }
}
