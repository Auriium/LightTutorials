package xyz.auriium.opentutorial.core.stage.plainkeyword;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.ProcessingInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlainKeywordInsertion implements ProcessingInsertion {

    PlainKeywordInsertion() {}

    public static PlainKeywordInsertion INIT = new PlainKeywordInsertion();

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

        List<String> keywords = Interpret.getRequired("keywords",map, shitter -> {

            List<String> strings = new ArrayList<>();

            for (FlexibleType type : shitter.getList()) {
                strings.add(type.getString());
            }

            return strings;
        });

        int maxDelay = Interpret.getEllusive("maxDelay",map,FlexibleType::getInteger,Interpret.NO_INT);
        boolean isCancelOnFail = Interpret.getEllusive("isCancelOnFail",map,FlexibleType::getBoolean,false);

        return new PlainKeywordStage(keywords,maxDelay,isCancelOnFail);
    }
}
