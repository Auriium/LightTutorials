package xyz.auriium.opentutorial.stage.action;

import xyz.auriium.opentutorial.centralized.config.tutorials.Interpret;
import xyz.auriium.opentutorial.stage.StageSerializer;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

import java.util.Map;

public class CommandStageSerializer implements StageSerializer<CommandStage> {
    @Override
    public String identifier() {
        return "command";
    }

    @Override
    public CommandStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        String runAsConsole = Interpret.getEllusive("run_as_console",map,FlexibleType::getString, Interpret.NO_STRING);
        String runAsPlayer = Interpret.getEllusive("run_as_player",map,FlexibleType::getString, Interpret.NO_STRING);

        return new CommandStage(runAsConsole,runAsPlayer);
    }
}
