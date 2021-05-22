package me.aurium.opentutorial.centralized;

import me.aurium.opentutorial.stage.Stage;
import me.aurium.opentutorial.stage.StageConsumer;

import java.util.UUID;

/**
 * Manages all tutorials
 */
public interface TutorialController {

    Tutorial getByUUID(UUID uuid);

    void consume(Stage stage);

    void register(StageConsumer<?> consumer);

}
