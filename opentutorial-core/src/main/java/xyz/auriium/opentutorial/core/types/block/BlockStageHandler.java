package xyz.auriium.opentutorial.core.types.block;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.AbstractDelayConsumer;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.Map;

public class BlockStageHandler extends AbstractDelayConsumer<BlockStage, PlatformlessBlockEvent> {
    @Override
    public void consume(BlockStage stage, PlatformlessBlockEvent event, Tutorial tutorial) {
        if (event.getX() == stage.getX() && event.getY() == stage.getY() && event.getZ() == stage.getZ()) {
            tutorial.fireNext();
        }
    }

    @Override
    public void started1(BlockStage stage, Tutorial tutorial) {

    }

    @Override
    public String identifier() {
        return "block";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        int x = Interpret.getRequired(Identifiers.LOC_X,map,FlexibleType::getInteger);
        int y = Interpret.getRequired(Identifiers.LOC_Y,map,FlexibleType::getInteger);
        int z = Interpret.getRequired(Identifiers.LOC_Z,map,FlexibleType::getInteger);
        Integer maxDelay = Interpret.getNullable(Identifiers.DELAYTYPE_MAX_DELAY,map,FlexibleType::getInteger);
        String actionbarFormat = Interpret.getNullable(Identifiers.DELAYTYPE_FORMAT, map, FlexibleType::getString);

        return new BlockStage(x,y,z,maxDelay,actionbarFormat);
    }

    @Override
    public Class<BlockStage> stageClass() {
        return BlockStage.class;
    }

    @Override
    public Class<PlatformlessBlockEvent> eventClass() {
        return PlatformlessBlockEvent.class;
    }
}
