package xyz.auriium.opentutorial.core.config.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.platform.base.AudienceRegistry;

public class WarningExceptionHandler implements ConfigExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger("OpenTutorial");

    private final AudienceRegistry audienceRegistry;

    public WarningExceptionHandler(AudienceRegistry audienceRegistry) {
        this.audienceRegistry = audienceRegistry;
    }

    @Override
    public void signal() {
        audienceRegistry.getAllAccessibleAudiences().forEach(audience -> audience.sendMessage("&cAn error occurred trying to read your OpenTutorial config! Please check console for the stacktrace (long error-like message) as it has instructions on how to fix your config or seek help with the error!"));
    }
}
