package xyz.auriium.opentutorial.centralized.config.messages;

import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface ConfMessages {

    @ConfKey("messages.tutorials.invalid_template")
    @ConfDefault.DefaultString("&9OpenTutorial &7> &c%template% is not a valid template!")
    Message invalidTemplateMessage();

    @ConfKey("messages.stage_defaults.out_of_time")
    @ConfDefault.DefaultString("&9OpenTutorial &7> &cOut of time! You have failed the %tutorial% tutorial!")
    Message outOfTimeMessage();

}
