package xyz.auriium.opentutorial.stage.await;

import xyz.auriium.opentutorial.stage.Stage;

public interface AwaitStage extends Stage {

    long getMaxDelay();
    String getOutOfTimeMessage();

}
