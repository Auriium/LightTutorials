package xyz.auriium.opentutorial.core.types.keyword;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.openmineplatform.api.interfaceable.user.User;
import xyz.auriium.opentutorial.core.StageExceptionMapper;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.AbstractDelayConsumer;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.List;
import java.util.Map;

public class PlainKeywordStageHandler extends AbstractDelayConsumer<PlainKeywordStage, PlatformlessChatEvent> {
    @Override
    public void consume(PlainKeywordStage stage, PlatformlessChatEvent event, Tutorial tutorial) {
        String message = event.getMessage();

        for (String matchable : stage.getMatchables()) {
            if (message.toLowerCase().contains(matchable.toLowerCase())) {
                tutorial.fireNext();
                return;
            }
        }

        if (stage.isCancelOnFail()) {
            tutorial.fireCancel();
        }

        stage.getCommandOnFail().ifPresent(s -> {
            User user = tutorial.getPlatform()
                    .interRegistry()
                    .getTelescoping(tutorial.getIdentifier(), StageExceptionMapper.USER);

            user.getName().map(a -> s.replaceAll("%PLAYER%", a)).ifPresent(user::runCommandAsPlatform);
        });
    }

    @Override
    public void started1(PlainKeywordStage stage, Tutorial tutorial) {

    }

    @Override
    public String identifier() {
        return "plain_keyword";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        List<String> keywords = Interpret.getRequired(Identifiers.LIST_KEYWORDS,map,Interpret::convertList);
        Integer maxDelay = Interpret.getNullable(Identifiers.DELAYTYPE_MAX_DELAY,map,FlexibleType::getInteger);
        boolean isCancelOnFail = Interpret.getAlternative(Identifiers.FAIL_CANCEL,map,FlexibleType::getBoolean,true);
        String commandOnFail = Interpret.getNullable(Identifiers.FAIL_COMMAND,map,FlexibleType::getString);
        String actionbarFormat = Interpret.getNullable(Identifiers.DELAYTYPE_FORMAT, map, FlexibleType::getString);


        return new PlainKeywordStage(keywords, isCancelOnFail, commandOnFail, maxDelay, actionbarFormat);
    }

    @Override
    public Class<PlainKeywordStage> stageClass() {
        return PlainKeywordStage.class;
    }

    @Override
    public Class<PlatformlessChatEvent> eventClass() {
        return PlatformlessChatEvent.class;
    }
}
