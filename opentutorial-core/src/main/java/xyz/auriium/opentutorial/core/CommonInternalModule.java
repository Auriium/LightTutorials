package xyz.auriium.opentutorial.core;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.opentutorial.core.config.CommonConfigController;
import xyz.auriium.opentutorial.core.config.ConfigController;

public class CommonInternalModule implements InternalDependentModule {

    private final ConfigController configController;

    CommonInternalModule(ConfigController configController) {
        this.configController = configController;
    }

    @Override
    public ConfigController configController() {
        return configController;
    }

    public static InternalDependentModule launch(Platform platform, ConsumerRegistry consumerRegistry) {
        ConfigController configController = CommonConfigController.launch(platform, consumerRegistry);

        return new CommonInternalModule(configController);
    }

}
