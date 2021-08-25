package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.beetle.utility.aspect.UUIDCloseable;
import xyz.auriium.opentutorial.core.event.Event;
import xyz.auriium.opentutorial.core.tutorial.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.UUID;

/**
 * Represents a physical consumer of stage logic that handles when to progress to the next stage.
 *
 * MUST IMPLEMENT StageConsumer#started WITH Tutorial#fireNext or the tutorial will never complete until shutdown!~ //TODO choked tutorial handler
 *
 * @param <T> type of stage consumed
 */
public interface StageConsumer<T extends Stage> extends UUIDCloseable {



    /**
     * Method that describes logic that handles when a new stage of type T is started.
     * This method is called on the begin of said stage.
     *
     * To mark the stage as complete, you must fire the method {@link Tutorial#fireNext()} or the stage will never complete and the tutorial
     * will never finish.
     *
     * If the player fails the stage you are currently processing you must fire {@link Tutorial#fireCancel()}
     *
     * @param options the options
     * @param continuable the tutorial reference.
     */
    void started(T options, Tutorial continuable);

    /**
     * The corresponding stage class. Used as key internally
     * @return the class
     */
    Class<T> stageClass();

    @Override
    default void close() {

    }

    @Override
    default void closeSingle(UUID uuid) {

    }
}
