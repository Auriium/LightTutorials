package me.aurium.opentutorial.stage.response;

import me.aurium.opentutorial.stage.StageSerializer;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

import java.util.Map;

public class ResponseSerializer implements StageSerializer<ResponseStage> {
    @Override
    public String identifier() {
        return "response_stage";
    }

    @Override
    public ResponseStage deserialize(Map<String, FlexibleType> map) throws BadValueException {



        return null;
    }
}
