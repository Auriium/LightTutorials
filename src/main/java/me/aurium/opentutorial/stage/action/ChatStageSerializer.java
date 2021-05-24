package me.aurium.opentutorial.stage.action;

import me.aurium.opentutorial.centralized.config.Interpret;
import me.aurium.opentutorial.stage.StageSerializer;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

import java.util.Map;

public class ChatStageSerializer implements StageSerializer<ChatStage> {
    @Override
    public String identifier() {
        return "chat_stage";
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
