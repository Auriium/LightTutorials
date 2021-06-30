package xyz.auriium.opentutorial.core.stage.command;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.ProcessingInsertion;
import xyz.auriium.opentutorial.api.construct.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;

public class CommandStageInsertion implements ProcessingInsertion {

    CommandStageInsertion() {}

    public static CommandStageInsertion INIT = new CommandStageInsertion();

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
        String runAsConsole = Interpret.getEllusive("run_as_console",map,FlexibleType::getString, Interpret.NO_STRING);
        String runAsPlayer = Interpret.getEllusive("run_as_player",map,FlexibleType::getString, Interpret.NO_STRING);

        return new CommandStage(runAsConsole,runAsPlayer);
    }
}
