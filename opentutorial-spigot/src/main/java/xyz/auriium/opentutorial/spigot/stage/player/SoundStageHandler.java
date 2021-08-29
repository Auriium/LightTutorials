package xyz.auriium.opentutorial.spigot.stage.player;

import org.bukkit.entity.Player;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.openmineplatform.api.interfaceable.user.User;
import xyz.auriium.openmineplatform.spigot.user.PlayerTelescope;
import xyz.auriium.opentutorial.core.StageExceptionMapper;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.BasicConsumer;
import xyz.auriium.opentutorial.core.consumer.StageException;
import xyz.auriium.opentutorial.core.consumer.stage.Defaults;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.Map;

public class SoundStageHandler implements BasicConsumer<SoundStage> {
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

    @Override
    public void stageStarted(SoundStage options, Tutorial tutorial) throws StageException {
        User user = tutorial.getPlatform()
                .interRegistry()
                .getTelescoping(tutorial.getIdentifier(), StageExceptionMapper.USER);

        //TODO playsound

        Player player = user.telescope(PlayerTelescope.EXCEPTIONAL);
        player.playSound(player.getLocation(), options.getSoundString(), options.getPitch(), options.getVolume());

        tutorial.fireNext();
    }

    @Override
    public Class<SoundStage> stageClass() {
        return SoundStage.class;
    }
}
