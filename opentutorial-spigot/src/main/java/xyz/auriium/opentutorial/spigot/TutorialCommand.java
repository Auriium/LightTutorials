package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.entity.Player;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;
import xyz.auriium.opentutorial.core.MissingServiceSupplier;
import xyz.auriium.opentutorial.core.PlatformDependentModule;
import xyz.auriium.opentutorial.core.config.MessageConfig;
import xyz.auriium.opentutorial.core.event.EventBus;
import xyz.auriium.opentutorial.core.template.Template;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.types.clickable.PlatformlessClickableEvent;
import xyz.auriium.opentutorial.spigot.hook.command.ListMenu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * What an ugly class - let's use branch!
 */
@CommandAlias("tutorial|opentutorial")
public class TutorialCommand extends BaseCommand {

    private final ReloadablePluginState state;
    private final Platform platform;
    private final ListMenu listMenu;

    public TutorialCommand(ReloadablePluginState state, Platform platform) {
        this.state = state;
        this.platform = platform;
        this.listMenu = new ListMenu(platform, platform.colorer());
    }


    @Subcommand("list")
    @CommandPermission("opentutorial.list")
    public void list(Player player) {
        listMenu.produce().show(player);
    }

    @HelpCommand
    @CommandPermission("opentutorial.help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("reload")
    @CommandPermission("opentutorial.reload")
    public void reload(@Optional Player player) {

        try {
            state.reload();

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();

            if (player != null) {
                platform.serviceRegistry()
                        .retrieve(PlatformDependentModule.class)
                        .orElseThrow(new MissingServiceSupplier("plugin-core"))
                        .configController()
                        .getMessageConfig()
                        .reloadMessage()
                        .send(platform.interRegistry().get(player.getUniqueId()), formatter.format(date));
            }

        } catch (Exception e) {
            //I SADLY HAVE TO DO THIS FUCKING SHITCODE
            //BECAUSE ACF SUCKS ASS AND FUCKS WITH EXCEPTION HANDLING ANYWAYS
            //SO I HAVE TO DO IT MYSELF IN THIS CRINGE ASS try-catch ABUSE
            //FUCK AIKAR AND HIS SHITCODE
            //CANT WAIT TO SWITCH TO BRANCH

            //FUCK!!!!!!!!

            //the tf2 coconut of opentutorial

            platform.logger().error("Error: " + e);
        }

    }

    @Subcommand("play")
    @CommandPermission("opentutorial.play")
    @CommandCompletion("@templates")
    public void play(Player sender, Template template, @Optional Player target) {
        Player user = target == null ? sender : target;

        PlatformDependentModule module = platform.serviceRegistry()
                .retrieve(PlatformDependentModule.class)
                .orElseThrow(new MissingServiceSupplier("plugin-core"));

        TutorialController controller = module.tutorialController();

        if (controller.getByUUID(user.getUniqueId()).isPresent()) {
            module.configController().getMessageConfig().alreadyInTutorialMessage().send(platform.interRegistry().get(sender.getUniqueId()));
            return;
        }

        controller.createNew(template,user.getUniqueId()).fireNext();
    }

    @Subcommand("playpoint")
    @CommandPermission("opentutorial.play")
    @CommandCompletion("@templates")
    public void playPoint(Player sender, Template template, int point, @Optional Player target) {
        Player user = target == null ? sender : target;

        PlatformDependentModule module = platform.serviceRegistry()
                .retrieve(PlatformDependentModule.class)
                .orElseThrow(new MissingServiceSupplier("plugin-core"));

        TutorialController tutorialController = module.tutorialController();
        UUID uuid = user.getUniqueId();

        int subpoint = point - 1;

        if (tutorialController.getByUUID(uuid).isPresent()) {
            module.configController()
                    .getMessageConfig()
                    .alreadyInTutorialMessage()
                    .send(platform.interRegistry().get(uuid));

            return;
        }

        if (template.stageNotPresent(subpoint)) {
            module.configController()
                    .getMessageConfig()
                    .invalidStageMessage()
                    .send(platform.interRegistry().get(uuid),point,template);

            return;
        }

        tutorialController.createStage(template, uuid, subpoint);

    }

    @Subcommand("option")
    public void option(Player sender, int option) {
        UUID uuid = sender.getUniqueId();

        PlatformDependentModule module = platform.serviceRegistry()
                .retrieve(PlatformDependentModule.class)
                .orElseThrow(new MissingServiceSupplier("plugin-core"));

        TutorialController tutorialController = module.tutorialController();
        MessageConfig messageConfig = module.configController().getMessageConfig();
        EventBus bus = module.eventBus();

        tutorialController.getByUUID(uuid).ifPresentOrElse(
                tutorial -> bus.fire(new PlatformlessClickableEvent(option, sender.getUniqueId()),tutorial),
                () -> messageConfig.notInTutorialMessage().send(platform.interRegistry().get(sender.getUniqueId()))
        );


    }

    @Subcommand("quit")
    @CommandPermission("opentutorial.quit")
    public void leave(Player sender) {
        UUID uuid = sender.getUniqueId();

        PlatformDependentModule module = platform.serviceRegistry()
                .retrieve(PlatformDependentModule.class)
                .orElseThrow(new MissingServiceSupplier("plugin-core"));

        TutorialController tutorialController = module.tutorialController();
        MessageConfig messageConfig = module.configController().getMessageConfig();

        if (tutorialController.getByUUID(uuid).isEmpty()) {
            messageConfig.notInTutorialMessage().send(platform.interRegistry().get(uuid));
            return;
        }


        messageConfig.leftTutorialMessage().send(platform.interRegistry().get(uuid));
        tutorialController.cancelByUUID(uuid);
    }

}
