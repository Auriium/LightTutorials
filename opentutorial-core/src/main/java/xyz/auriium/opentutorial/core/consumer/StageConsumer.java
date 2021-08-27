package xyz.auriium.opentutorial.core.consumer;

import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

/**
 * Base level stage consumer that represents handling logic for when a stage is fired
 * MUST INVOKE {@link Tutorial#fireNext()} or the tutorial will not progress
 *
 * @param <T> type of stage consumed
 */
public interface StageConsumer<T extends Stage, E> extends StageConfigParser {


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
     */
    void stageStarted(T options, Tutorial tutorial) throws StageException;
    void eventCalled(E event, Tutorial tutorial) throws StageException;

    /**
     * The corresponding stage class. Used as key internally
     * @return the class
     */
    Class<T> stageClass();
    Class<E> eventClass();
    boolean isRegisterable();

}
