package xyz.auriium.opentutorial.stage.response;

import xyz.auriium.opentutorial.centralized.config.tutorials.Interpret;
import xyz.auriium.opentutorial.stage.StageSerializer;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlainKeywordSerializer implements StageSerializer<PlainKeywordStage> {
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
        String outOfTimeMessage = Interpret.getEllusive("outOfTimeMessage",map,FlexibleType::getString,Interpret.DEFAULT_OUT_OF_TIME);

        return new PlainKeywordStage(keywords,maxDelay,isCancelOnFail, outOfTimeMessage);
    }
}
