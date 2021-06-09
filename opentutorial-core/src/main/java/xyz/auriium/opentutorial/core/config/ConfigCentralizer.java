package xyz.auriium.opentutorial.core.config;

import xyz.auriium.opentutorial.core.config.types.general.GeneralConfig;
import xyz.auriium.opentutorial.core.config.types.tutorials.TutorialsConfig;
import xyz.auriium.opentutorial.core.model.Cycleable;
import xyz.auriium.opentutorial.core.config.types.messages.MessageConfig;

public interface ConfigCentralizer extends Cycleable {

    MessageConfig getMessageConfig();
    GeneralConfig getGeneralConfig();

}
