package xyz.auriium.opentutorial.core.stage.clickquiz;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.api.construct.Stage;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.stage.Defaults;
import xyz.auriium.opentutorial.core.stage.Identifiers;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClickableStageInsertion implements StageInsertion {
    @Override
    public String identifier() {
        return "clickable";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        List<String> keywords = Interpret.getRequired(Identifiers.LIST_KEYWORDS,map,Interpret::convertList);

        int correctOption = Interpret.getRequired(Identifiers.CORRECT_OPTION,map,FlexibleType::getInteger);
        Integer maxDelay = Interpret.getNullable(Identifiers.DELAYTYPE_MAX_DELAY,map,FlexibleType::getInteger);
        boolean isCancelOnFail = Interpret.getAlternative(Identifiers.FAIL_CANCEL,map,FlexibleType::getBoolean,false);
        String commandOnFail = Interpret.getNullable(Identifiers.FAIL_COMMAND,map,FlexibleType::getString);
        String actionbarFormat = Interpret.getNullable(Identifiers.DELAYTYPE_FORMAT, map, FlexibleType::getString);

        return new ClickableQuizStage(keywords, correctOption, isCancelOnFail, commandOnFail, maxDelay, actionbarFormat);
    }

    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new ClickableQuizConsumer(platform.scheduler(), platform.userRegistry(), configController.getMessageConfig());
    }
}
