package xyz.auriium.opentutorial.core.tutorial;

import org.slf4j.Logger;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.opentutorial.core.MissingServiceSupplier;
import xyz.auriium.opentutorial.core.consumer.StageException;
import xyz.auriium.opentutorial.core.platform.MessagingExceptionHandler;

public class CancellingConduit implements ConsumerFailureConduit{

    private final MessagingExceptionHandler handler;
    private final Logger logger;

    CancellingConduit(MessagingExceptionHandler handler, Logger logger) {
        this.handler = handler;
        this.logger = logger;
    }

    @Override
    public void handle(Tutorial tutorial, FailureSupplier supplier) {
        try {
            supplier.act();
        } catch (StageException e) {
            logger.error("An exception was thrown while enacting a stage!");

            tutorial.fireCancel();

            handler.failStage(e);
        }
    }

    public static CancellingConduit launch(Platform platform) {
        MessagingExceptionHandler handler = platform.serviceRegistry()
                .retrieve(MessagingExceptionHandler.class)
                .orElseThrow(new MissingServiceSupplier("exception-handler"));

        return new CancellingConduit(handler, platform.logger());
    }
}
