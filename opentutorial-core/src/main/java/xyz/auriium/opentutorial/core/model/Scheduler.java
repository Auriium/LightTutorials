package xyz.auriium.opentutorial.core.model;

public interface Scheduler {

    SchedulerTask run(Runnable runnable);
    SchedulerTask runLater(Runnable runnable, long delay);
    SchedulerTask runRepeated(Runnable runnable, long period);

}
