package xyz.auriium.opentutorial.core.types.player;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.BasicConsumer;
import xyz.auriium.opentutorial.core.consumer.StageException;
import xyz.auriium.opentutorial.core.consumer.StageMissingServiceSupplier;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.consumer.stage.StageLocalValue;
import xyz.auriium.opentutorial.core.platform.UserChatSuppressor;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.Map;
import java.util.UUID;

public class SuppressStageHandler implements BasicConsumer<SuppressStage> {

    @Override
    public String identifier() {
        return "suppress";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        boolean x = Interpret.getRequired(Identifiers.SUPPRESS_CHAT,map,FlexibleType::getBoolean);

        return new SuppressStage(x);
    }

    @Override
    public void stageStarted(SuppressStage options, Tutorial tutorial) throws StageException {

        boolean suppress = options.isSuppressChat();
        UUID uuid = tutorial.getIdentifier();

        UserChatSuppressor userChatSuppressor = tutorial.getPlatform()
                .serviceRegistry()
                .retrieve(UserChatSuppressor.class)
                .orElseThrow(new StageMissingServiceSupplier("Suppressor"));

        tutorial.localStorage().register("suppressor", new StageLocalValue<>(suppress, false, bool -> {
            if (bool) {
                userChatSuppressor.removeOne(uuid);
            }
        }));

        userChatSuppressor.setSuppressed(uuid,options.isSuppressChat());

        tutorial.fireNext();
    }

    @Override
    public Class<SuppressStage> stageClass() {
        return SuppressStage.class;
    }
}
