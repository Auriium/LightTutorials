package xyz.auriium.opentutorial.core.config.types.tutorials;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.Decomposer;
import space.arim.dazzleconf.serialiser.FlexibleType;
import space.arim.dazzleconf.serialiser.ValueSerialiser;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

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

        String type = Optional.ofNullable(stageConfigMap.get("type")).orElseThrow(() -> new BadValueException.Builder().key("type").message("Stage missing value for type! Add `type:` to the stage!")
                .build()).getString();

        StageSerializer<?> serializer = registry.getSerializer(type).orElseThrow(() -> new BadValueException.Builder().key(type).message("Could not find a stage with that type!").build());

        return serializer.deserialize(stageConfigMap);
    }

    @Override
    public Object serialise(Stage stage, Decomposer decomposer) {
        throw new UnsupportedOperationException("Not implemented! (Yet!)");
    }
}
