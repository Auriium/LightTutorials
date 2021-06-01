package xyz.auriium.opentutorial.centralized.config.tutorials;

import space.arim.dazzleconf.annote.ConfKey;

import java.util.List;
import java.util.Map;

/**
 * File for all tutorials
 */
public interface ConfTutorials {

    @ConfKey("tutorials")
    Map<String,ConfTemplate> getTemplates();

}
