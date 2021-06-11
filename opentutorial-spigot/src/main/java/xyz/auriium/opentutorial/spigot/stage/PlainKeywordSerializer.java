package xyz.auriium.opentutorial.spigot.stage;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.types.tutorials.Interpret;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

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

        return new PlainKeywordStage(keywords,maxDelay,isCancelOnFail);
    }
}
