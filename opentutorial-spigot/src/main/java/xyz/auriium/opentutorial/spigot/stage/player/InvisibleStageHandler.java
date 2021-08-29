package xyz.auriium.opentutorial.spigot.stage.player;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.openmineplatform.api.interfaceable.user.User;
import xyz.auriium.openmineplatform.spigot.user.PlayerTelescope;
import xyz.auriium.opentutorial.core.StageExceptionMapper;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.BasicConsumer;
import xyz.auriium.opentutorial.core.consumer.StageException;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.consumer.stage.StageLocalValue;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.Map;

public class InvisibleStageHandler implements BasicConsumer<InvisibleStage> {
    @Override
    public String identifier() {
        return "invisible";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        boolean enabled = Interpret.getRequired(Identifiers.INVISIBLE, map, FlexibleType::getBoolean);

        return new InvisibleStage(enabled);
    }

    @Override
    public void stageStarted(InvisibleStage options, Tutorial tutorial) throws StageException {
        boolean on = options.isOn();

        User user = tutorial.getPlatform()
                .interRegistry()
                .getTelescoping(tutorial.getIdentifier(), StageExceptionMapper.USER);

        tutorial.localStorage()
                .register("invisibility", new StageLocalValue<>(on, false, val -> {

                    if (val) {
                        user.telescope(PlayerTelescope.OPTIONAL).ifPresent(player -> player.setInvisible(false));
                    }

                }));

        user.telescope(PlayerTelescope.OPTIONAL).ifPresent(player -> player.setInvisible(on));

        tutorial.fireNext();
    }

    @Override
    public Class<InvisibleStage> stageClass() {
        return InvisibleStage.class;
    }
}
