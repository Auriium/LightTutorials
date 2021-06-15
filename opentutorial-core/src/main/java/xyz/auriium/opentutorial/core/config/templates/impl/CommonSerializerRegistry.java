package xyz.auriium.opentutorial.core.config.templates.impl;

import xyz.auriium.opentutorial.core.config.templates.SerializerRegistry;
import xyz.auriium.opentutorial.core.stage.age.AgeStageSerializer;
import xyz.auriium.opentutorial.core.stage.chat.ChatStageSerializer;
import xyz.auriium.opentutorial.core.stage.clickblock.ClickBlockSerializer;
import xyz.auriium.opentutorial.core.stage.command.CommandStageSerializer;
import xyz.auriium.opentutorial.core.stage.delay.DelaySerializer;
import xyz.auriium.opentutorial.core.stage.invisible.InvisibleStageSerializer;
import xyz.auriium.opentutorial.core.stage.teleport.TeleportStageSerializer;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonSerializerRegistry implements SerializerRegistry {
    private final Map<String, StageSerializer<?>> serializers = new HashMap<>();

    @Override
    public Optional<StageSerializer<?>> getSerializer(String identifier) {
        return Optional.ofNullable(serializers.get(identifier));
    }

    @Override
    public <E extends Stage> SerializerRegistry register(StageSerializer<E> serializer) {
        serializers.put(serializer.identifier(),serializer);

        return this;
    }

    public static SerializerRegistry defaults() {
        return new CommonSerializerRegistry()
                .register(new AgeStageSerializer())
                .register(new ChatStageSerializer())
                .register(new ClickBlockSerializer())
                .register(new CommandStageSerializer())
                .register(new DelaySerializer())
                .register(new InvisibleStageSerializer())
                .register(new TeleportStageSerializer());
    }
}
