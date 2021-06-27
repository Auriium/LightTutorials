package xyz.auriium.opentutorial.core.stage.chat;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.ProcessingInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;

public class ChatStageInsertion implements ProcessingInsertion {

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
        String chat = Interpret.getEllusive("chat", map, FlexibleType::getString, Interpret.NO_STRING);
        String actionbar = Interpret.getEllusive("actionbar", map, FlexibleType::getString, Interpret.NO_STRING);
        String title = Interpret.getEllusive("title", map, FlexibleType::getString, Interpret.NO_STRING);
        String subtitle = Interpret.getEllusive("subtitle", map, FlexibleType::getString, Interpret.NO_STRING);

        return new ChatStage(chat,actionbar,title,subtitle);
    }
}
