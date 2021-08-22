package xyz.auriium.opentutorial.core.stage.playsound;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.stage.Defaults;
import xyz.auriium.opentutorial.core.stage.Identifiers;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;
import xyz.auriium.opentutorial.api.construct.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;

public class SoundStageInsertion implements StageInsertion {

    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new SoundStageConsumer(platform.userRegistry());
    }

    @Override
    public String identifier() {
        return "sound";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        String sound = Interpret.getRequired(Identifiers.SOUND_NAME,map,FlexibleType::getString);
        float volume = Interpret.getAlternative(Identifiers.SOUND_VOLUME,map,FlexibleType::getFloat, Defaults.DEFAULT_SOUND_VOLUME);
        float pitch = Interpret.getAlternative(Identifiers.SOUND_PITCH,map,FlexibleType::getFloat,Defaults.DEFAULT_SOUND_PITCH);

        return new SoundStage(sound,volume,pitch);
    }
}
