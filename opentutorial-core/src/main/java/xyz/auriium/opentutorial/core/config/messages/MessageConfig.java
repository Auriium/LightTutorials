package xyz.auriium.opentutorial.core.config.messages;

import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface MessageConfig {

    @ConfKey("messages.reload")
    @ConfDefault.DefaultString("&9OpenTutorial &7> &fReload complete! &7[&e%s&7]")
    Message reloadMessage();

    @ConfKey("messages.tutorials.invalid_template")
    @ConfDefault.DefaultString("&9OpenTutorial &7> &c%s is not a valid template!")
    Message invalidTemplateMessage();

    @ConfKey("messages.tutorials.invalid_stage")
    @ConfDefault.DefaultString("&9OpenTutorial &7> &cNo stage found at index %d in tutorial %s!")
    Message invalidStageMessage();

    @ConfKey("messages.tutorials.not_in_tutorial")
    @ConfDefault.DefaultString("&9OpenTutorial &7> &cYou are not in a tutorial!")
    Message notInTutorialMessage();

    @ConfKey("messages.tutorials.already_in_tutorial")
    @ConfDefault.DefaultString("&9OpenTutorial &7> &cYou are already in a tutorial!")
    Message alreadyInTutorialMessage();

    @ConfKey("messages.tutorials.complete")
    @ConfDefault.DefaultString("&9OpenTutorial &7> &fYou have completed the tutorial!")
    Message completedTutorialMessage();

    @ConfKey("messages.stage_defaults.out_of_time")
    @ConfDefault.DefaultString("&9OpenTutorial &7> &cOut of time! You have failed the %s tutorial!")
    Message outOfTimeMessage();

    @ConfKey("messages.stage_defaults.not_a_number")
    @ConfDefault.DefaultString("&9OpenTutorial &7> &cThat is not a valid number!")
    Message notNumberMessage();



}
