package xyz.auriium.opentutorial.stage.response;

import xyz.auriium.opentutorial.stage.StageSerializer;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

import java.util.Map;

public class PlainKeywordSerializer implements StageSerializer<PlainKeywordStage> {
    @Override
    public String identifier() {
        return "plain_keyword";
    }

    @Override
    public PlainKeywordStage deserialize(Map<String, FlexibleType> map) throws BadValueException {



        return null;
    }
}
