package xyz.auriium.opentutorial.core.config;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface GeneralConfig {

    @ConfComments("Default tutorial to play on a player's first join. Leave as none to play no tutorial.")
    @ConfDefault.DefaultString("newbie")
    @ConfKey("options.default_tutorial")
    String defaultTutorial();

    @ConfComments("Permission required to be placed in the first join tutorial. Leave as none for no permission.")
    @ConfDefault.DefaultString("opentutorial.first_join")
    @ConfKey("options.default_tutorial_permission")
    String defaultPermission();


}
