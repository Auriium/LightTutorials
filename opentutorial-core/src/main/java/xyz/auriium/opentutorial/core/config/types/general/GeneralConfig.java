package xyz.auriium.opentutorial.core.config.types.general;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface GeneralConfig {

    @ConfComments("Selects a default tutorial to play on a player's first join")
    @ConfDefault.DefaultString("newbie")
    @ConfKey("options.default_tutorial")
    String defaultTutorial();

    @ConfComments("Whether the default tutorial is enabled or not")
    @ConfDefault.DefaultBoolean(false)
    @ConfKey("options.default_enabled")
    boolean defaultEnabled();

}
