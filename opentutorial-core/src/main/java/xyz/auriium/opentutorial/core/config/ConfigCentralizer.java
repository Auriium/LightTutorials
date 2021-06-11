package xyz.auriium.opentutorial.core.config;

import xyz.auriium.opentutorial.core.config.templates.TutorialsConfig;
import xyz.auriium.opentutorial.core.model.Cycleable;
import xyz.auriium.opentutorial.core.config.messages.MessageConfig;

public interface ConfigCentralizer extends Cycleable {

    //i hate working with null bullshit
    ConfigHolder<MessageConfig> getMessageConfig();
    ConfigHolder<GeneralConfig> getGeneralConfig();
    ConfigHolder<TutorialsConfig> getTutorialsConfig();

}
