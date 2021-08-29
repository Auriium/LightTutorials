package xyz.auriium.opentutorial.core.consumer;

import xyz.auriium.openmineplatform.api.interfaceable.user.User;
import xyz.auriium.opentutorial.core.consumer.stage.Delayable;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public class RepeatedRunnable implements Runnable {

    private final User teachable;
    private final Delayable delayable;
    private final Tutorial continuable;
    private int val;

    public RepeatedRunnable(User teachable, Delayable delayable, Tutorial continuable) {
        this.teachable = teachable;
        this.delayable = delayable;

        val = delayable.getDelay() / 2;
        this.continuable = continuable;
    }

    @Override
    public void run() {

        val--;

        int tens = val / 10;
        int ones = val - (tens * 10);


        String format = delayable.getDelayFormat();
        if (format != null) {
            teachable.sendActionbar(String.format(format, tens, ones));
        }

        if (val <= 0) {
            continuable.fireNext();
        }
    }

}
