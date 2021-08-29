package xyz.auriium.opentutorial.core.types.player;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.openmineplatform.api.binding.location.UnboundPlatformLocation;
import xyz.auriium.opentutorial.core.StageExceptionMapper;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.BasicConsumer;
import xyz.auriium.opentutorial.core.consumer.StageException;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.Map;

public class TeleportStageHandler implements BasicConsumer<TeleportStage> {
    @Override
    public String identifier() {
        return "teleport";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        Long x = Interpret.getRequired(Identifiers.LOC_X,map,FlexibleType::getLong);
        Long y = Interpret.getRequired(Identifiers.LOC_Y,map,FlexibleType::getLong);
        Long z = Interpret.getRequired(Identifiers.LOC_Z,map,FlexibleType::getLong);
        String world = Interpret.getNullable(Identifiers.LOC_WORLD,map,FlexibleType::getString);
        Long pitch = Interpret.getNullable(Identifiers.LOC_PITCH,map,FlexibleType::getLong);
        Long yaw = Interpret.getNullable(Identifiers.LOC_YAW,map,FlexibleType::getLong);

        return new TeleportStage(UnboundPlatformLocation.of(x, y, z, pitch, yaw, world));
    }

    @Override
    public void stageStarted(TeleportStage options, Tutorial tutorial) throws StageException {
        tutorial.getPlatform()
                .interRegistry()
                .getTelescoping(tutorial.getIdentifier(), StageExceptionMapper.USER)
                .teleport(options.getLocation()); //TODO fix this next release

        tutorial.fireNext();
    }

    @Override
    public Class<TeleportStage> stageClass() {
        return TeleportStage.class;
    }
}
