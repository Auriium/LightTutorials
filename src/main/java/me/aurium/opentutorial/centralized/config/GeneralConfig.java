package me.aurium.opentutorial.centralized.config;

import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface GeneralConfig {

    @ConfDefault.DefaultString("none")
    @ConfKey("options.default_tutorial")
    String tutorialOnStart();
}
