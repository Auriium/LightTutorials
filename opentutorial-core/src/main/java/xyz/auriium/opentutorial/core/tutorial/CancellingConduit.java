package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.opentutorial.core.MissingServiceSupplier;
import xyz.auriium.opentutorial.core.platform.MessagingExceptionHandler;
import xyz.auriium.opentutorial.core.consumer.StageException;

public class CancellingConduit implements ConsumerFailureConduit{

    private final MessagingExceptionHandler handler;

    CancellingConduit(MessagingExceptionHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle(Tutorial tutorial, FailureSupplier supplier) {
        try {
            supplier.act();
        } catch (StageException e) {
            tutorial.fireCancel();

            handler.failStage(e);
        }
    }

    public static CancellingConduit launch(Platform platform) {
        MessagingExceptionHandler handler = platform.serviceRegistry()
                .retrieve(MessagingExceptionHandler.class)
                .orElseThrow(new MissingServiceSupplier("exception-handler"));

        return new CancellingConduit(handler);
    }
}
