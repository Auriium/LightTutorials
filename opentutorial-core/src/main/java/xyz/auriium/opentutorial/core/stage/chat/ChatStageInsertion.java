package xyz.auriium.opentutorial.core.stage.chat;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;

public class ChatStageInsertion implements StageInsertion {

    ChatStageInsertion() {}

    public static ChatStageInsertion INIT = new ChatStageInsertion();

    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new ChatStageConsumer(platform.userRegistry());
    }

    @Override
    public String identifier() {
        return "chat";
    }

    @Override
    public ChatStage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        String chat = Interpret.getNullable("chat", map, FlexibleType::getString);
        String actionbar = Interpret.getNullable("actionbar", map, FlexibleType::getString);
        String title = Interpret.getNullable("title", map, FlexibleType::getString);
        String subtitle = Interpret.getNullable("subtitle", map, FlexibleType::getString);

        return new ChatStage(chat,actionbar,title,subtitle);
    }
}
