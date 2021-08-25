package xyz.auriium.opentutorial.core.tutorial.stage.keyword;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.Identifiers;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;

import java.util.List;
import java.util.Map;

public class PlainKeywordInsertion implements StageInsertion {


    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new PlainKeywordStageConsumer(platform.scheduler(), platform.userRegistry(), configController.getMessageConfig());
    }

    @Override
    public String identifier() {
        return "plain_keyword";
    }

    @Override
    public PlainKeywordStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        List<String> keywords = Interpret.getRequired(Identifiers.LIST_KEYWORDS,map,Interpret::convertList);

        Integer maxDelay = Interpret.getNullable(Identifiers.DELAYTYPE_MAX_DELAY,map,FlexibleType::getInteger);
        boolean isCancelOnFail = Interpret.getAlternative(Identifiers.FAIL_CANCEL,map,FlexibleType::getBoolean,true);
        String commandOnFail = Interpret.getNullable(Identifiers.FAIL_COMMAND,map,FlexibleType::getString);
        String actionbarFormat = Interpret.getNullable(Identifiers.DELAYTYPE_FORMAT, map, FlexibleType::getString);


        return new PlainKeywordStage(keywords, isCancelOnFail, commandOnFail, maxDelay, actionbarFormat);
    }
}
