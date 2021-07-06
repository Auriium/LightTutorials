package xyz.auriium.opentutorial.core.stage.clickquiz;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.api.construct.Stage;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
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
        List<String> keywords = Interpret.getRequired("keywords",map, shitter -> {

            List<String> strings = new ArrayList<>();

            for (FlexibleType type : shitter.getList()) {
                strings.add(type.getString());
            }

            return strings;
        });

        int correctOption = Interpret.getRequired("correct_option",map,FlexibleType::getInteger);
        Long maxDelay = Interpret.getNullable("max_delay",map,FlexibleType::getLong);
        boolean isCancelOnFail = Interpret.getAlternative("cancel_on_fail",map,FlexibleType::getBoolean,false);
        String commandOnFail = Interpret.getNullable("command_on_fail",map,FlexibleType::getString);

        return new ClickableQuizStage(keywords, maxDelay, correctOption, isCancelOnFail,commandOnFail);
    }

    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new ClickableQuizConsumer(platform.scheduler(), platform.userRegistry(), configController.getMessageConfig());
    }
}
