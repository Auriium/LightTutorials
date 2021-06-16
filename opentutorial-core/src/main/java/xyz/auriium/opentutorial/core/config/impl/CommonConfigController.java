package xyz.auriium.opentutorial.core.config.impl;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.GeneralConfig;
import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.config.templates.TutorialsConfig;

public class CommonConfigController implements ConfigController {

    private final MessageConfig messageConfig;
    private final GeneralConfig generalConfig;
    private final TutorialsConfig tutorialsConfig;

    public CommonConfigController(MessageConfig messageConfig, GeneralConfig generalConfig, TutorialsConfig tutorialsConfig) {
        this.messageConfig = messageConfig;
        this.generalConfig = generalConfig;
        this.tutorialsConfig = tutorialsConfig;
    }

    @Override
    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    @Override
    public GeneralConfig getGeneralConfig() {
        return generalConfig;
    }

    @Override
    public TutorialsConfig getTutorialsConfig() {
        return tutorialsConfig;
    }
}
