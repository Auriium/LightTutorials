package xyz.auriium.opentutorial.core.platform;

/**
 * Represents a platform's task dispatcher
 */
public interface Scheduler {

    /**
     * Runs a task
     * @param runnable logic ot run
     * @return the task as a scheduler task
     */
    SchedulerTask run(Runnable runnable);

    /**
     * Runs a task in the future
     * @param runnable logic to run
     * @param delay delay before logic execution
     * @return scheduler task
     */
    SchedulerTask runLater(Runnable runnable, long delay);

    /**
     * Runs a task repeatedly until canceled
     * @param runnable logic to run
     * @param period delay between logic runs
     * @return scheduler task
     */
    SchedulerTask runRepeated(Runnable runnable, long period);

}
