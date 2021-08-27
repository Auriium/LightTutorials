package xyz.auriium.opentutorial.core.types.clickable;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.openmineplatform.api.interfaceable.user.User;
import xyz.auriium.opentutorial.core.StageExceptionMapper;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.AbstractDelayConsumer;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.List;
import java.util.Map;

public class ClickableQuizHandler extends AbstractDelayConsumer<ClickableQuizStage, PlatformlessClickableEvent> {

    @Override
    public void consume(ClickableQuizStage stage, PlatformlessClickableEvent event, Tutorial tutorial) {
        if (event.getClicked() == stage.getCorrectOption() - 1) {
            tutorial.fireNext();
        } else {
            User user = tutorial.getPlatform()
                    .interRegistry()
                    .getTelescoping(tutorial.getIdentifier(), StageExceptionMapper.USER);

            tutorial.getModule()
                    .configController()
                    .getMessageConfig()
                    .notAnswerMessage()
                    .send(user);

            if (stage.isCancelOnFail()) {
                tutorial.fireCancel();
            }

            stage.getCommandOnFail().ifPresent(user::runCommandAsPlatform);
        }
    }

    @Override
    public void started1(ClickableQuizStage stage, Tutorial tutorial) {
        User user = tutorial.getPlatform()
                .interRegistry()
                .getTelescoping(tutorial.getIdentifier(), StageExceptionMapper.USER);

        for (int i = 0; i < stage.getOptions().size(); i++) {
            String str = stage.getOptions().get(i);

            String message = tutorial.getModule()
                    .configController()
                    .getMessageConfig()
                    .optionMessage()
                    .parse(i + 1, str);

            user.sendClickable(message, "opentutorial option " + i);

        }
    }

    @Override
    public String identifier() {
        return "click_quiz";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        List<String> keywords = Interpret.getRequired(Identifiers.LIST_KEYWORDS,map,Interpret::convertList);
        int correctOption = Interpret.getRequired(Identifiers.CORRECT_OPTION,map,FlexibleType::getInteger);
        Integer maxDelay = Interpret.getNullable(Identifiers.DELAYTYPE_MAX_DELAY,map,FlexibleType::getInteger);
        boolean isCancelOnFail = Interpret.getAlternative(Identifiers.FAIL_CANCEL,map,FlexibleType::getBoolean,false);
        String commandOnFail = Interpret.getNullable(Identifiers.FAIL_COMMAND,map,FlexibleType::getString);
        String actionbarFormat = Interpret.getNullable(Identifiers.DELAYTYPE_FORMAT, map, FlexibleType::getString);

        return new ClickableQuizStage(keywords, correctOption, isCancelOnFail, commandOnFail, maxDelay, actionbarFormat);
    }

    @Override
    public Class<ClickableQuizStage> stageClass() {
        return ClickableQuizStage.class;
    }

    @Override
    public Class<PlatformlessClickableEvent> eventClass() {
        return PlatformlessClickableEvent.class;
    }
}
