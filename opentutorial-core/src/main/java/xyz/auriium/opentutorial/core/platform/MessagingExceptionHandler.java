package xyz.auriium.opentutorial.core.platform;

import space.arim.dazzleconf.error.InvalidConfigException;
import xyz.auriium.opentutorial.core.consumer.StageException;

import java.io.IOException;

public interface MessagingExceptionHandler {

    void failIO(IOException exception);
    void failConfig(InvalidConfigException exception);
    void failStage(StageException exception);

}
