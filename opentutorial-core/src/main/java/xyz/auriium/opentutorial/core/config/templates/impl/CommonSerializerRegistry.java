package xyz.auriium.opentutorial.core.config.templates.impl;

import xyz.auriium.opentutorial.core.config.templates.SerializerRegistry;
import xyz.auriium.opentutorial.core.stage.age.AgeStageInsertion;
import xyz.auriium.opentutorial.core.stage.chat.ChatStageInsertion;
import xyz.auriium.opentutorial.core.stage.clickblock.ClickBlockInsertion;
import xyz.auriium.opentutorial.core.stage.command.CommandStageInsertion;
import xyz.auriium.opentutorial.core.stage.delay.DelayStageInsertion;
import xyz.auriium.opentutorial.core.stage.invisible.InvisibleStageInsertion;
import xyz.auriium.opentutorial.core.stage.lock.LockableStageInsertion;
import xyz.auriium.opentutorial.core.stage.plainkeyword.PlainKeywordInsertion;
import xyz.auriium.opentutorial.core.stage.playsound.SoundStageInsertion;
import xyz.auriium.opentutorial.core.stage.teleport.TeleportStageInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonSerializerRegistry implements SerializerRegistry {
    private final Map<String, StageInsertion> serializers = new HashMap<>();

    @Override
    public Optional<StageInsertion> getSerializer(String identifier) {
        return Optional.ofNullable(serializers.get(identifier));
    }

    @Override
    public SerializerRegistry register(StageInsertion serializer) {
        serializers.put(serializer.identifier(),serializer);

        return this;
    }

    public static SerializerRegistry defaults() {
        return new CommonSerializerRegistry()
                .register(AgeStageInsertion.INIT)
                .register(ChatStageInsertion.INIT)
                .register(ClickBlockInsertion.INIT)
                .register(CommandStageInsertion.INIT)
                .register(DelayStageInsertion.INIT)
                .register(InvisibleStageInsertion.INIT)
                .register(PlainKeywordInsertion.INIT)
                .register(SoundStageInsertion.INIT)
                .register(new LockableStageInsertion())
                .register(TeleportStageInsertion.INIT);
    }
}
