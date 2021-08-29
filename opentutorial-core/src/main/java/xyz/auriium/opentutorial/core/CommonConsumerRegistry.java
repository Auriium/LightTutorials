package xyz.auriium.opentutorial.core;

import xyz.auriium.opentutorial.core.consumer.StageConfigParser;
import xyz.auriium.opentutorial.core.consumer.StageConsumer;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.types.block.BlockStageHandler;
import xyz.auriium.opentutorial.core.types.chat.ChatStageHandler;
import xyz.auriium.opentutorial.core.types.clickable.ClickableQuizHandler;
import xyz.auriium.opentutorial.core.types.command.CommandStageHandler;
import xyz.auriium.opentutorial.core.types.delay.ActionbarDelayStageHandler;
import xyz.auriium.opentutorial.core.types.delay.DelayStageHandler;
import xyz.auriium.opentutorial.core.types.keyword.PlainKeywordStageHandler;
import xyz.auriium.opentutorial.core.types.player.LockableStageHandler;
import xyz.auriium.opentutorial.core.types.player.SuppressStageHandler;
import xyz.auriium.opentutorial.core.types.player.TeleportStageHandler;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CommonConsumerRegistry implements ConsumerRegistry {

    private final Map<Class<?>, StageConsumer<?,?>> map = new ConcurrentHashMap<>();

    @Override
    public Collection<StageConsumer<?, ?>> getAll() {
        return map.values();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Stage, E> Optional<StageConsumer<T, E>> get(Class<T> clazz) {
        return Optional.ofNullable((StageConsumer<T,E>) map.get(clazz));
    }

    @Override
    public Optional<StageConfigParser> getInsertion(String insertionName) {
        //shitty search pattern
        for (StageConfigParser parser : map.values()) {
            if (parser.identifier().equals(insertionName)) {
                return Optional.of(parser);
            }
        }
        return Optional.empty();
    }

    @Override
    public ConsumerRegistry register(StageConsumer<?, ?> consumer) {
        map.put(consumer.stageClass(), consumer);

        return this;
    }

    public static ConsumerRegistry defaults() {
        return new CommonConsumerRegistry()
                .register(new BlockStageHandler())
                .register(new ChatStageHandler())
                .register(new ClickableQuizHandler())
                .register(new CommandStageHandler())
                .register(new ActionbarDelayStageHandler())
                .register(new DelayStageHandler())
                .register(new PlainKeywordStageHandler())
                .register(new LockableStageHandler())
                .register(new SuppressStageHandler())
                .register(new TeleportStageHandler());
    }
}
