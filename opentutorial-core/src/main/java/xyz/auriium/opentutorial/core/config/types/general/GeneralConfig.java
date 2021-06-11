package xyz.auriium.opentutorial.core.config.types.general;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface GeneralConfig {

    @ConfComments("Selects a default tutorial to play on a player's first join")
    @ConfDefault.DefaultString("newbie")
    @ConfKey("options.default_tutorial")
    String defaultTutorial();

    @ConfComments("Permission required to be placed in the first join tutorial. Leave as none for no permission.")
    @ConfDefault.DefaultString("opentutorial.first_join")
    @ConfKey("options.default_tutorial_permission")
    String defaultPermission();

    @ConfComments("Whether the default tutorial is enabled or not")
    @ConfDefault.DefaultBoolean(false)
    @ConfKey("options.default_enabled")
    boolean defaultEnabled();

}
