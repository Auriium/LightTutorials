package xyz.auriium.opentutorial.core.platform;

/**
 * Adapter interface for tasks provided by a platform
 */
public interface SchedulerTask {

    /**
     * Cancels a running task
     */
    void cancel();

}
