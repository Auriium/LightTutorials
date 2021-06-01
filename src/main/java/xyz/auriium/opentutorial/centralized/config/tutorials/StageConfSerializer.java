package xyz.auriium.opentutorial.centralized.config.tutorials;

import xyz.auriium.opentutorial.centralized.registry.ConsumerRegistry;
import xyz.auriium.opentutorial.stage.Stage;
import xyz.auriium.opentutorial.stage.StageSerializer;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.Decomposer;
import space.arim.dazzleconf.serialiser.FlexibleType;
import space.arim.dazzleconf.serialiser.ValueSerialiser;

import java.util.Map;
import java.util.Optional;

public class StageConfSerializer implements ValueSerialiser<Stage> {

    private final ConsumerRegistry registry;

    public StageConfSerializer(ConsumerRegistry registry) {
        this.registry = registry;
    }

    @Override
    public Class<Stage> getTargetClass() {
        return Stage.class;
    }

    @Override
    public Stage deserialise(FlexibleType value) throws BadValueException {
        Map<String, FlexibleType> stageConfigMap = value.getMap((key, v) -> Map.entry(key.getString(), v));

        String type = Optional.ofNullable(stageConfigMap.get("type")).orElseThrow(() -> new BadValueException.Builder().key("type").message("Stage missing type!")
                .build()).getString();

        StageSerializer<?> serializer = registry.getSerializer(type).orElseThrow(() -> new BadValueException.Builder().key(type).message("Invalid type - could not find a serializer of type!").build());

        return serializer.deserialize(stageConfigMap);
    }

    @Override
    public Object serialise(Stage stage, Decomposer decomposer) {
        throw new UnsupportedOperationException("Not implemented! (Yet!)");
    }
}
