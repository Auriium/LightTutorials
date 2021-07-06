package xyz.auriium.opentutorial.core.config.messages;

import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfHeader;
import space.arim.dazzleconf.annote.ConfKey;

@ConfHeader("Messages configuration. Set any message to 'none' in order to make it simply not be sent. ")
public interface MessageConfig {

    @ConfKey("messages.reload")
    @ConfDefault.DefaultString("&9&lOpen&7&lTutorial &7> &fReload complete! &7[&e%s&7]")
    Message reloadMessage();

    @ConfKey("messages.tutorials.left")
    @ConfDefault.DefaultString("&9&lOpen&7&lTutorial &7> &fYou left the tutorial!")
    Message leftTutorialMessage();

    @ConfKey("messages.tutorials.invalid_template")
    @ConfDefault.DefaultString("&9&lOpen&7&lTutorial &7> &c%s is not a valid template!")
    Message invalidTemplateMessage();

    @ConfKey("messages.tutorials.template_already_exists")
    @ConfDefault.DefaultString("&9&lOpen&7&lTutorial &7> &cA template already exists with that name!")
    Message templateExistsMessage();

    @ConfKey("messages.tutorials.invalid_stage")
    @ConfDefault.DefaultString("&9&lOpen&7&lTutorial &7> &cNo stage found at index %d in tutorial %s!")
    Message invalidStageMessage();

    @ConfKey("messages.tutorials.not_in_tutorial")
    @ConfDefault.DefaultString("&9&lOpen&7&lTutorial &7> &cYou are not in a tutorial!")
    Message notInTutorialMessage();

    @ConfKey("messages.tutorials.already_in_tutorial")
    @ConfDefault.DefaultString("&9&lOpen&7&lTutorial &7> &cYou are already in a tutorial!")
    Message alreadyInTutorialMessage();

    @ConfKey("messages.tutorials.complete")
    @ConfDefault.DefaultString("&9&lOpen&7&lTutorial &7> &fYou have completed the tutorial!")
    Message completedTutorialMessage();

    @ConfKey("messages.stage_defaults.out_of_time")
    @ConfDefault.DefaultString("&9&lOpen&7&lTutorial &7> &cOut of time! You have failed the %s tutorial!")
    Message outOfTimeMessage();

    @ConfKey("messages.stage_defaults.not_a_number")
    @ConfDefault.DefaultString("&9&lOpen&7&lTutorial &7> &cThat is not a valid number!")
    Message notNumberMessage();

    @ConfKey("messages.stage_defaults.not_correct")
    @ConfDefault.DefaultString("&9&lOpen&7&lTutorial &7> &cThat answer is incorrect!")
    Message notAnswerMessage();

    @ConfKey("messages.stage_defaults.clickable_option")
    @ConfDefault.DefaultString("&7[&9%s&7]&e %s")
    Message optionMessage();



}
