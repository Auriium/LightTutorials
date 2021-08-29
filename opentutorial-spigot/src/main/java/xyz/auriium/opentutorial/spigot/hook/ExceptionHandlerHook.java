package xyz.auriium.opentutorial.spigot.hook;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.ServiceHook;
import xyz.auriium.opentutorial.core.platform.MessagingExceptionHandler;
import xyz.auriium.opentutorial.core.platform.WarningExceptionHandler;

public class ExceptionHandlerHook implements ServiceHook<MessagingExceptionHandler> {
    @Override
    public Class<MessagingExceptionHandler> providedServiceType() {
        return MessagingExceptionHandler.class;
    }

    @Override
    public MessagingExceptionHandler provide(Platform platform) {
        return new WarningExceptionHandler(platform.interRegistry());
    }

    @Override
    public String name() {
        return "exception-handler-hook";
    }
}
