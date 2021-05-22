package me.aurium.opentutorial.centralized.config;

import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface GeneralConfig {

    @ConfDefault.DefaultString("newbie")
    @ConfKey("options.default_tutorial")
    String defaultTutorial();

    @ConfDefault.DefaultBoolean(false)
    @ConfKey("options.default_enabled")
    boolean defaultEnabled();
}
