package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.beetle.utility.aspect.UUIDCloseable;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

/**
 * Represents a physical consumer of stage logic that handles when to progress to the next stage.
 *
 * MUST IMPLEMENT StageConsumer#started WITH Tutorial#fireNext or the tutorial will never complete until shutdown!~ //TODO choked tutorial handler
 *
 * @param <T> type of stage consumed
 */
public interface StageConsumer<T extends Stage> extends UUIDCloseable {

    void started(T options, Tutorial continuable);

    Class<T> stageClass();

}
