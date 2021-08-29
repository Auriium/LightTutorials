package xyz.auriium.opentutorial.spigot.stage.player;

import xyz.auriium.openmineplatform.api.binding.location.PlatformLocation;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;

import java.util.List;

public class HoloStage implements Stage {

    private final List<String> strings;

    private final PlatformLocation startingLocation;
    private final boolean followPlayer;
    private final Integer stayLong;

    public HoloStage(List<String> strings, PlatformLocation startingLocation, boolean followPlayer, Integer stayLong) {
        this.strings = strings;
        this.startingLocation = startingLocation;
        this.followPlayer = followPlayer;
        this.stayLong = stayLong;
    }

    public List<String> getStrings() {
        return strings;
    }

    public boolean isFollowPlayer() {
        return followPlayer;
    }

    public Integer getStayLong() {
        return stayLong;
    }

    public PlatformLocation getStartingLocation() {
        return startingLocation;
    }
}
