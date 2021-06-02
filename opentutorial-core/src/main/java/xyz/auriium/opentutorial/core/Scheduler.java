package xyz.auriium.opentutorial.core;

public interface Scheduler {

    SimpleTask run(Runnable runnable);
    SimpleTask runLater(Runnable runnable, long delay);
    SimpleTask runRepeated(Runnable runnable, long period);

}
