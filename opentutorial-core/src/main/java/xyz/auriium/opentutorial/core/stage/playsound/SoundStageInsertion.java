package xyz.auriium.opentutorial.core.stage.playsound;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.ProcessingInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;

public class SoundStageInsertion implements ProcessingInsertion {

    SoundStageInsertion() {}

    public static SoundStageInsertion INIT = new SoundStageInsertion();

    @Override
    public StageConsumer<?> build(Platform platform, ConfigController configController) {
        return new SoundStageConsumer(platform.userRegistry());
    }

    @Override
    public String identifier() {
        return "sound";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        String sound = Interpret.getRequired("sound_name",map,FlexibleType::getString);
        float volume = Interpret.getEllusive("volume",map,FlexibleType::getFloat,1.0f);
        float pitch = Interpret.getEllusive("pitch",map,FlexibleType::getFloat,1.0f);

        return new SoundStage(sound,volume,pitch);
    }
}
