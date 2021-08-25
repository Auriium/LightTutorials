package xyz.auriium.opentutorial.core.tutorial.stage.player;

import xyz.auriium.opentutorial.core.tutorial.Stage;

/**
 * Stage that plays a certain sound key from the resource pack to the player.
 */
public class SoundStage implements Stage {

    private final String soundString;
    private final float volume;
    private final float pitch;

    public SoundStage(String soundString, float volume, float pitch) {
        this.soundString = soundString;
        this.volume = volume;
        this.pitch = pitch;
    }

    public String getSoundString() {
        return soundString;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }
}
