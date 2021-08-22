package xyz.auriium.opentutorial.core.stage.command;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Constants;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.stage.Identifiers;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;
import xyz.auriium.opentutorial.api.construct.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;

public class CommandStageInsertion implements StageInsertion {
    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new CommandStageConsumer(platform.userRegistry());
    }

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
}
