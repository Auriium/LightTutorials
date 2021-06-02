package xyz.auriium.opentutorial.spigot.stage;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.types.tutorials.Interpret;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

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
