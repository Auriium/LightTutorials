package xyz.auriium.opentutorial.centralized.config;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface ConfGeneral {

    @ConfComments("Selects a default tutorial to play on a player's first join")
    @ConfDefault.DefaultString("newbie")
    @ConfKey("options.default_tutorial")
    String defaultTutorial();

    @ConfComments("Whether the default tutorial is enabled or not")
    @ConfDefault.DefaultBoolean(false)
    @ConfKey("options.default_enabled")
    boolean defaultEnabled();

    @ConfComments("Whether to use the custom config error handler or not. If it is, you will get a pretty message during config failures sent to both the console and you. Otherwise, you will get an exception in the console that can be sent to me, the developer for analysis and repair!")
    @ConfDefault.DefaultBoolean(true)
    @ConfKey("options.use_custom_config_errors")
    boolean useCustomExceptionHandler();
}
