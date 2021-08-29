package xyz.auriium.opentutorial.core.config;

import xyz.auriium.opentutorial.core.config.templates.TutorialsConfig;

public interface ConfigController {

    //i hate working with null bullshit
    MessageConfig getMessageConfig();
    GeneralConfig getGeneralConfig();
    TutorialsConfig getTutorialsConfig();

}
