package xyz.auriium.opentutorial.core.config.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;

public class WarningExceptionHandler implements ConfigExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger("OpenTutorial");

    private final TeachableRegistry teachableRegistry;

    public WarningExceptionHandler(TeachableRegistry teachableRegistry) {
        this.teachableRegistry = teachableRegistry;
    }

    @Override
    public void signal() {
        teachableRegistry.getAllAccessibleAudiences().forEach(audience -> audience.sendMessage("&cAn error occurred trying to read your OpenTutorial config! Please check console for the stacktrace (long error-like message) as it has instructions on how to fix your config or seek help with the error!"));
    }
}
